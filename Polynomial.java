import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Polynomial {
	double []coefficients;
	int []exponents;
	
	public Polynomial() {
		coefficients = new double[1];
		exponents = new int[1];
		coefficients[0] = 0;
		exponents[0] = 0;
	}
	
	public Polynomial(double []co, int []ex) {
		coefficients = new double[co.length];
		exponents = new int[ex.length];
		for(int i = 0; i < co.length; i++) {
			coefficients[i] = co[i];
			exponents[i] = ex[i];
		}
	}
	
	public Polynomial(File f) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader(f));
		String text = r.readLine();
		r.close();
		String text_split[] = text.split("(?=-)|\\+");
		String term[];
		
		coefficients = new double[text_split.length];
		exponents = new int[text_split.length];
		
		int index = 0;
		for(String x:text_split) {
			term = x.split("x");
			coefficients[index] = Double.parseDouble(term[0]);
			if(term.length == 2)
				exponents[index] = Integer.parseInt(term[1]);
			index++;
		}
	}
	
	public Polynomial add(Polynomial b) {
		int length = exponents.length;
		double [] c;
		int [] e;
		Polynomial out;
		
		if(Arrays.equals(exponents, b.exponents)) {
			c = new double[length];
			e = new int[length];
			
			for(int i = 0; i < length; i++) {
				c[i] = coefficients[i] + b.coefficients[i];
				e[i] = exponents[i];
			}
		}
		else {
			boolean match;
			
			for(int i = 0; i < exponents.length; i++) {
				match = false;
				for(int k = 0; k < b.exponents.length; k++) {
					if(exponents[i] == b.exponents[k])
						match = true;
				}
				if(!match) {
					length++;
				}
			}
			
			c = new double[length];
			e = new int[length];
			
			for(int i = 0; i < length; i++) {
				c[i] = 0;
			}
			
			int index = 0;
			for(int i = 0; i < exponents.length; i++) {
				e[i] = exponents[i];
				index = i;
			}
			
			match = false;
			for(int i = 0; i < b.exponents.length; i++) {
				for(int k = 0; k < exponents.length; k++) {
					if(b.exponents[i] == e[k]) {
						match = true;
					}
				}
				if(!match) {
					index++;
					e[index] = b.exponents[i];
				}
				match = false;
			}
			
			for(int i = 0; i < length; i++) {
				for(int k = 0; k < exponents.length; k++) {
					if(exponents[k] == e[i]) {
						c[i] += coefficients[k];
						break;
					}
					
				}
				
				for(int k = 0; k < b.exponents.length; k++) {
					if(b.exponents[k] == e[i]) {
						c[i] += b.coefficients[k];
						break;
					}
				}
			}
			
		}
		
		out = new Polynomial(c,e);
		out = removeZeroes(out, length);
		
		return out;
	}
	
	public double evaluate(double x) {
		double result = 0;
		for(int i = 0; i < coefficients.length; i++) {
			result += coefficients[i] * Math.pow(x, exponents[i]);
		}
		
		return result;
	}
	
	public boolean hasRoot(double x) {
		if(evaluate(x) == 0) {
			return true;
		}
		return false;
	}
	
	public Polynomial multiply(Polynomial b) {
		Polynomial out;
		int length = 3 * exponents.length;
		int e_slots[] = new int[length];
		
		double c[];
		int e[];
		
		boolean new_expo = true;
		int expo;
		int index = 0;
		for(int i = 0; i < exponents.length; i++) {
			for(int k = 0; k < b.exponents.length; k++) {
				expo = exponents[i] + b.exponents[k];
				for(int j = 0; j < length; j++) {
					if(expo == e_slots[j]) {
						new_expo = false;
						break;
					}
				}
				if(new_expo) {
					e_slots[index] = expo;
					index++;
				}
				new_expo = true;
			}
		}
		length = index;
		
		c = new double[length];
		e = new int[length];
		
		for(int i = 0; i < length; i++) {
			e[i] = e_slots[i];
		}
		
		for(int i = 0; i < exponents.length; i++) {
			for(int k = 0; k < b.exponents.length; k++) {
				expo = exponents[i] + b.exponents[k];
				for(int j = 0; j < length; j++) {
					if(e_slots[j] == expo) {
						c[j] += (coefficients[i] * b.coefficients[k]); 
						break;
					}
				}
			}
		}

		out = new Polynomial(c, e);
		out = out.removeZeroes(out, length);
		
		return out;
		
	}
	
	public Polynomial removeZeroes(Polynomial a, int length) {
		Polynomial out;
		int removed = 0;
		
		for(int i = 0; i < length; i++) {
			if(a.coefficients[i] == 0) {
				removed++;
			}
		}
		int new_length = length - removed;
		
		double []co = new double[new_length];
		int []ex = new int[new_length];
		int index = 0;
		for(int i = 0; i < length; i++) {
			if(a.coefficients[i] != 0) {
				co[index] = a.coefficients[i];
				ex[index] = a.exponents[i];
				index++;
			}
		}
		
		out = new Polynomial(co,ex);
		
		return out;
	}
	
	public void saveToFile(String fileName) throws Exception {
		String text = "";
		for(int i = 0; i < coefficients.length; i++) {
			if(coefficients[i] > 0 && i != 0)
				text = text + "+";
			text = text + coefficients[i];
			if(exponents[i] != 0)
				text = text + "x" + exponents[i];
		}
		
		PrintStream p = new PrintStream(fileName);
		p.println(text);
		p.close();
	}
	
	
	
}