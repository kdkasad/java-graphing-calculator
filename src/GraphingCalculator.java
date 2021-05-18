import javax.swing.*;
import java.awt.*;

/**
 * Represents a graphing calculator window/instance
 *
 * @author Kian Kasad
 * @version 2021-05-17
 */
public class GraphingCalculator extends JFrame {
	private static final String WINDOW_TITLE = "Graphing Calculator";
	private static final Color BACKGROUND_COLOR = new Color(0x1d, 0x1e, 0x20); // #1d1e20
	private static final Color FOREGROUND_COLOR = new Color(0xd6, 0xd6, 0xd6); // #d6d6d6

	private GraphPanel gp;

	/**
	 * Create a graphing calculator
	 * @param width initial width of the window
	 * @param height initial height of the window
	 */
	public GraphingCalculator(int width, int height) {
		super(WINDOW_TITLE);
		setVisible(false);
		setSize(width, height);
		setBackground(BACKGROUND_COLOR);
		setForeground(FOREGROUND_COLOR);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		gp = new GraphPanel(BACKGROUND_COLOR, FOREGROUND_COLOR);
		gp.setEnabled(false);
		add(gp);
	}

	/**
	 * Run the graphing calculator
	 */
	public void run() {
		gp.setEnabled(true);
		setVisible(true);
	};

	public void stop() {
		setVisible(false);
		gp.setEnabled(false);
	}

	/**
	 * Main method to create one graphing calculator window
	 */
	public static void main(String[] args) {
		GraphingCalculator gc = new GraphingCalculator(640, 480);
		gc.run();
	}
}
