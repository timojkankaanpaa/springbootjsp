package vsg;

public class MyLocation{
	private double x;
	private double y;
	
	public MyLocation(){
		
	}
	
	public MyLocation(double _x, double _y){
		setX(_x);
		setY(_y);
		
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
