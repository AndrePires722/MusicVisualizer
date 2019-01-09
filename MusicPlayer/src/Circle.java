
public class Circle implements Measurable{
	private double radius;
	public Circle(double r) {
		radius = r;
	}
	public Circle() {
		this(0);
	}
	@Override
	public double getPerimiter() {
		return Math.PI * radius * 2;
	}
	@Override
	public double getArea() {
		return Math.PI * radius * radius;
	}
	
}
