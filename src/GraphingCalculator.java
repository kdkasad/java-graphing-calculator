import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Represents a graphing calculator window/instance
 *
 * @author Kian Kasad
 * @version 2021-05-17
 */
public class GraphingCalculator extends JFrame implements ActionListener {
	private static final String DEFAULT_EQUATION = "y = sin(x)";
	private static final String WINDOW_TITLE = "Graphing Calculator";
	private static final Color BACKGROUND_COLOR = new Color(0x1d, 0x1e, 0x20); // #1d1e20
	private static final Color FOREGROUND_COLOR = new Color(0xd6, 0xd6, 0xd6); // #d6d6d6

	private GraphPanel gp;
	private JTextField tf;
	private JButton btn;

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

		SpringLayout pLayout = new SpringLayout();
		Panel p = new Panel();
		p.setLayout(pLayout);
		p.setBackground(BACKGROUND_COLOR);

		tf = new JTextField(DEFAULT_EQUATION);
		tf.addActionListener(this);
		p.add(tf);

		btn = new JButton("Graph");
		btn.addActionListener(this);
		p.add(btn);

		/* set layout constraints */
		pLayout.putConstraint(SpringLayout.NORTH, tf, 5, SpringLayout.NORTH, p);
		pLayout.putConstraint(SpringLayout.WEST, tf, 5, SpringLayout.WEST, p);
		pLayout.putConstraint(SpringLayout.WEST, btn, 5, SpringLayout.EAST, tf);
		pLayout.putConstraint(SpringLayout.NORTH, btn, 5, SpringLayout.NORTH, p);
		pLayout.putConstraint(SpringLayout.EAST, p, 5, SpringLayout.EAST, btn);
		pLayout.putConstraint(SpringLayout.SOUTH, p, 5, SpringLayout.SOUTH, btn);
		pLayout.putConstraint(SpringLayout.SOUTH, tf, 0, SpringLayout.SOUTH, btn);

		add(p, BorderLayout.NORTH);

		gp = new GraphPanel(BACKGROUND_COLOR, FOREGROUND_COLOR, new Equation(DEFAULT_EQUATION));
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
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}

		GraphingCalculator gc = new GraphingCalculator(640, 480);
		gc.run();
	}

	/**
	 * Called when the button is pressed.
	 * Updates the equation in the graph panel.
	 * @param ev information about the event
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		try {
			Equation e = new Equation(tf.getText());
			gp.setEquation(e);
		} catch (IllegalArgumentException e) {}
	}
}
