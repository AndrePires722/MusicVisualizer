
public class Rectangle implements Measurable{
	private double width, height;
	public Rectangle(double w, double h) {
		width = w;
		height = h;
	}
	public Rectangle() {
		this(0,0);
	}
	@Override
	public double getPerimiter() {
		return 2*width + 2*height;
	}

	@Override
	public double getArea() {
		return width * height;
	}

}
