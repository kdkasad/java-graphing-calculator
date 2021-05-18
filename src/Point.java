/**
 * Represents a 2D point
 * @author Kian Kasad
 * @version 2021-05-17
 */
public class Point {
	/**
	 * x-coordinate of the point
	 */
	public double x;

	/**
	 * y-coordinate of the point
	 */
	public double y;

	/**
	 * Create a point with the given coordinates
	 * @param x inital value of the x-coordinate
	 * @param y inital value of the y-coordinate
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Create a string representation of the point
	 * @return the resulting string
	 */
	public String toString() {
		return String.format("(%.2f, %.2f)", x, y);
	}
}
