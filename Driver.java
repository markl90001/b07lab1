import java.io.File;

public class Driver {
	public static void main(String [] args) throws Exception {
		double [] c1 = {6,5};
		int [] e1 = {0,3};
		Polynomial p1 = new Polynomial(c1,e1);
		double [] c2 = {-2,-9};
		int [] e2 = {1,4};
		Polynomial p2 = new Polynomial(c2,e2);
		Polynomial s = p1.add(p2);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		for(int i = 0; i < s.coefficients.length; i++)
			System.out.print(s.coefficients[i] + " ");
		System.out.println();
		for(int i = 0; i < s.exponents.length; i++)
			System.out.print(s.exponents[i] + " ");
		System.out.println("\n");
		Polynomial m = p1.multiply(p2);
		for(int i = 0; i < m.coefficients.length; i++)
			System.out.print(m.coefficients[i] + " ");
		System.out.println();
		for(int i = 0; i < m.exponents.length; i++)
			System.out.print(m.exponents[i] + " ");
		
		System.out.println("\n");
		File f = new File("C:\\Users\\markl\\eclipse-workspace\\CSCB07\\src\\test.txt");
		
		Polynomial n = new Polynomial(f);
		for(double x:n.coefficients)
			System.out.print(x + " ");
		System.out.println();
		for(int x:n.exponents)
			System.out.print(x + " ");
		System.out.println();
		s.saveToFile("a");
	}
}
