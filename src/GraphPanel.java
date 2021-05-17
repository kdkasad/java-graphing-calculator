import java.awt.*;
import java.util.*;
import javax.swing.*;

public class GraphPanel extends JPanel {
	private static final Color BACKGROUND_COLOR = new Color(0x1d, 0x1e, 0x20); // #1d1e20
	private static final Color FOREGROUND_COLOR = new Color(0xd6, 0xd6, 0xd6); // #d6d6d6

	public GraphPanel() {
		super();
		setBackground(BACKGROUND_COLOR);
		setForeground(FOREGROUND_COLOR);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
	}
}
