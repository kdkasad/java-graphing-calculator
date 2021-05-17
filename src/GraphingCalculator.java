import javax.swing.JFrame;

public class GraphingCalculator extends JFrame {
	private static final String WINDOW_TITLE = "Graphing Calculator";

	private GraphPanel gp;

	public GraphingCalculator(int width, int height) {
		super(WINDOW_TITLE);
		setSize(width, height);
	}

	public void run() {
	};

	public static void main(String[] args) {
		Equation e = new Equation("y = 2x");
		double inputs[] = { 1, 2, 3, 4, 5 };
		for (double x : inputs)
			System.out.printf("(%f, %f)\n", x, e.evaluate(x));
	}
}
