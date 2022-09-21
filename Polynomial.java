public class Polynomial {
	double []coefficients;
	
	public Polynomial() {
		coefficients = new double[1];
		coefficients[0] = 0;
	}
	
	public Polynomial(double []co) {
		coefficients = new double[co.length];
		for(int i = 0; i < co.length; i++) {
			coefficients[i] = co[i];
		}
		
	}
	
	public Polynomial add(Polynomial b) {
		int length;
		double [] c;
		Polynomial out;
		if(coefficients.length == b.coefficients.length) {
			length = coefficients.length;
			c = new double[length];
			for(int i = 0; i < length; i++) {
				c[i] = coefficients[i] + b.coefficients[i];
			}
		}
		else if(coefficients.length > b.coefficients.length) {
			length = coefficients.length;
			c = new double[length];
			double [] b2 = new double[length];
			for(int i = 0; i < length; i++) {
				if(i < b.coefficients.length)
					b2[i] = b.coefficients[i];
				else
					b2[i] = 0;
			}
			for(int i = 0; i < length; i++) {
				c[i] = coefficients[i] + b2[i];
			}
		}
		else {
			length = b.coefficients.length;
			c = new double[length];
			double [] a2 = new double[length];
			for(int i = 0; i < length; i++) {
				if(i < coefficients.length)
					a2[i] = coefficients[i];
				else
					a2[i] = 0;
			}
			for(int i = 0; i < length; i++) {
				c[i] = a2[i] + b.coefficients[i];
			}
			
		}
		out = new Polynomial(c);
		
		return out;
	}
	
	public double evaluate(double x) {
		double result = 0;
		for(int i = 0; i < coefficients.length; i++) {
			result += coefficients[i] * Math.pow(x, i);
		}
		
		return result;
	}
	
	public boolean hasRoot(double x) {
		if(evaluate(x) == 0) {
			return true;
		}
		return false;
	}
	
}