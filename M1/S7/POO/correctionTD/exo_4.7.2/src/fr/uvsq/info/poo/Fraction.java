package fr.uvsq.info.poo;

public class Fraction {
	private final int numerator;
	private final int denominator;
	public static Fraction ZERO = new Fraction(0);
	public static Fraction ONE= new Fraction(1);
	
	public Fraction() {
		this(0);
	}
	
	public Fraction(int numerator, int denominator) {
		int gcd = egcd(Math.abs(numerator), Math.abs(denominator));
		this.numerator = numerator / gcd;
		this.denominator = denominator / gcd;
	}
	
	public Fraction(int numerator) {
		this(numerator, 1);
	}
	
	public int getNumerator() {
		return this.numerator;
	}
	
	public int getDenominator() {
		return this.denominator;
	}
	
	public double doubleValue() {
		return (double) this.getNumerator() / this.getDenominator();
	}
	
	public static int egcd(int a, int b) {
		// Taken from https://stackoverflow.com/questions/4009198/java-get-greatest-common-divisor
		//assert a > 0;
		//assert b > 0;
		if (a < 0) {
			throw new IllegalArgumentException("egcd(a = " + a +", b = " + b + " : invalid a");
		}
		if (b < 0) {
			throw new IllegalArgumentException("egcd(a = " + a +", b = " + b + " : invalid b");
		}
	    if (a == 0) {
	        return b;
	    }
	    if (b == 0) {
	        return a;
	    }

	    while (b != 0) {
	        if (a > b) {
	            a = a - b;
	        } else {
	            b = b - a;
	        }
	    }
	    
	    return a;
	}
	
	public Fraction add(Fraction f) {
		if (f == null) {
			throw new IllegalArgumentException("add(f): f is null");
		}
		int n1 = this.getNumerator();
		int d1 = this.getDenominator();
		int n2 = f.getNumerator();
		int d2 = f.getDenominator();
		return new Fraction(n1 * d2 + n2 * d1, d1 * d2);
	}
	
	public Fraction substract(Fraction f) {
		if (f == null) {
			throw new IllegalArgumentException("compareTo(f): f is null");
		}
		int n1 = this.getNumerator();
		int d1 = this.getDenominator();
		int n2 = f.getNumerator();
		int d2 = f.getDenominator();
		return new Fraction(n1 * d2 - n2 * d1, d1 * d2);
	}
	
	public String toString() {
		return this.getDenominator() != 1 ?
				this.getNumerator() + "/" + this.getDenominator() :
				"" + this.getNumerator();
	}
	
	public boolean equalsTo(Fraction f) {
		if (f == null) {
			throw new IllegalArgumentException("equalsTo(f): f is null");
		}
		return this.getDenominator() == f.getDenominator()
			&& this.getNumerator()   == f.getNumerator();
	}
	
	public int compareTo(Fraction f) {
		if (f == null) {
			throw new IllegalArgumentException("compareTo(f): f is null");
		}
		Fraction sub = this.substract(f);
		int n = sub.getNumerator();
		return (n < 0) ? -1 :
			   (n > 0) ?  1 :
			   0;
	}
	
	public static void main(String[] args) {
		
		Fraction fdefault = new Fraction(); // 0
		System.out.println("fdefault = " + fdefault);
		
		Fraction fint = new Fraction(7); // 7
		System.out.println("fint = " + fint);
		
		Fraction f1 = new Fraction(3, 4); // 3/4
		System.out.println("f1 = " + f1);
		System.out.println("f1 = " + f1.doubleValue());
		
		Fraction f2 = new Fraction(2, 4); // 2/4
		System.out.println("f2 = " + f2);
		System.out.println("f2 = " + f2.doubleValue());
		
		// Addition
		Fraction sum = f1.add(f2);
		System.out.println("f1 + f2 = " + sum);
		System.out.println("f1 + f2 = " + sum.doubleValue());
		
		sum = f2.add(f2);
		System.out.println("f2 + f2 = " + sum);
		System.out.println("f2 + f2 = " + sum.doubleValue());
		
		// Equality
		System.out.println("(f1 == f1) = " + (f1.equalsTo(f1)));
		System.out.println("(f2 == f2) = " + (f2.equalsTo(f2)));
		System.out.println("(f1 == f2) = " + (f1.equalsTo(f2)));
		Fraction f3 = new Fraction(1, 2); // (f3 = 1/2) == (f2 = 3/4)
		System.out.println("(f2 " + f2 + " == f3 " + f3 + ") = " + (f2.equalsTo(f3)));
		
		// Substraction
		System.out.println("(f1 - f2) = " + (f1.substract(f2)));
		System.out.println("(f1 - f1) = " + (f1.substract(f1)));
		System.out.println("(f2 - f1) = " + (f2.substract(f1)));
		
		// CompareTo
		System.out.println("(f1 <= f1) = " + (f1.compareTo(f1)));
		System.out.println("(f2 <= f2) = " + (f2.compareTo(f2)));
		System.out.println("(f1 <= f2) = " + (f1.compareTo(f2)));
		System.out.println("(f2 <= f1) = " + (f2.compareTo(f1)));
		
		// ZERO, ONE
		Fraction zero = Fraction.ZERO;
		System.out.println("zero = " + zero);
		System.out.println("zero = " + zero.doubleValue());
		Fraction one = Fraction.ONE;
		System.out.println("one = " + one);
		System.out.println("zero = " + one.doubleValue());
		
		System.out.println("gcd(12, 9) = " + egcd(12,9));
	}
}
