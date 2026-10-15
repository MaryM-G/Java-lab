
class Complex {
	private double re, im;
	
	Complex(double re, double im){
		this.re = re;
		this.im = im;
	}
	
	public double getRE() {
		return re;
	}
	
	public double getIM() {
		return im;
	}
	
	public Complex addition(Complex a) {
		return new Complex (this.re + a.re, this.im + a.im);
	}
	
	public Complex subtraction(Complex a) {
		return new Complex(this.re - a.re, this.im - a.im);
	}
	
	public Complex multiplication(Complex a) {
		double newRe = this.re * a.re - this.im * a.im;
		double newIm = this.re * a.im + a.re * this.im;
		return new Complex (newRe, newIm);
	}
	
	public Complex division(Complex a) {
		double b = a.re * a.re + a.im * a.im;
		if (b == 0) {
			System.out.println("Деление на ноль!!!");
			return null;
		}
		double newRe = (this.re * a.re + this.im * a.im) / b;
		double newIm = (this.im * a.re - this.re * a.im) / b;
		return new Complex(newRe, newIm);
	}
	
	public String toString() {
		if (this.im >= 0) {
			return (this.re + "+" + this.im + "i");
		}  else {
			return (this.re + "" + this.im + "i");
		}
	}
}
