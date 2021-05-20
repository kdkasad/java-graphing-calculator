import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

/**
 * A JPanel containing a graph
 * @author Kian Kasad
 * @version 2021-05-17
 */
public class GraphPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {
	private static final double DEFAULT_LEVEL_OF_DETAIL = 0.01;
	private static final double DEFAULT_SCALE = 50;
	private static final double MIN_SCALE = 2;

	private double scale; /* pixels per graph unit */
	private double levelOfDetail;
	private Equation e;
	private Color fgcolor;
	private boolean tooltipEnabled;
	private Point tooltipMousePosition;

	/**
	 * Create a graph panel
	 *
	 * Defaults to graphing 'y = sin(x)', but can be changed using the {@link setEquation} function
	 *
	 * @param bg background color
	 * @param fg foreground color (used for graph lines)
	 * @param initialEquation initial equation
	 */
	public GraphPanel(Color bg, Color fg, Equation initialEquation) {
		super();
		setOpaque(true);
		setBackground(bg);
		setForeground(fg);
		fgcolor = fg;
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);

		scale = DEFAULT_SCALE;
		e = initialEquation;
		levelOfDetail = DEFAULT_LEVEL_OF_DETAIL;
		tooltipEnabled = false;
	}

	/**
	 * Set the equation to be graphed
	 * @param e the new equation
	 */
	public void setEquation(Equation e) {
		this.e = e;
		repaint();
	}

	/**
	 * Set the graph scale in pixels per unit.
	 * For example, a scale of 50 pixels per unit would mean that a window
	 * that is 1000 px wide would be able to graph 20 units (from -10 to
	 * 10).
	 *
	 * @param pixelsPerUnit the new scale value in pixels per graph unit
	 */
	public void setScale(double pixelsPerUnit) {
		scale = pixelsPerUnit;
	}

	/**
	 * Set the level of detail used when graphing.
	 * Lower levels of detail mean higher precision. The recommended value
	 * is 0.01, which is also the default, so you shouldn't need to use
	 * this method.
	 * @param newLevelOfDetail new level of detail to use
	 */
	public void setLevelOfDetail(double newLevelOfDetail) {
		levelOfDetail = newLevelOfDetail;
	}

	/**
	 * Convert from panel coordinates (i.e. drawing coordinates) to graph
	 * coordinates (i.e. points on the equation's graph).
	 * @param x x-value of the panel coordinate
	 * @param y y-value of the panel coordinate
	 * @return a {@link Point} containing the resulting graph coordinates
	 */
	private Point panelToGraphCoords(double x, double y) {
		return new Point(
				x / scale - getGraphWidth() / 2.0,
				getGraphHeight() / 2.0 - y / scale
		);
	}

	/**
	 * Convert from graph coordinates (i.e. points on the equation's graph)
	 * to panel coordinates (i.e. drawing coordinates).
	 * @param x x-value of the graph coordinate
	 * @param y y-value of the graph coordinate
	 * @return a {@link Point} containing the resulting panel coordinates
	 */
	private Point graphToPanelCoords(double x, double y) {
		return new Point(
				getWidth() / 2.0 + x * scale,
				getHeight() / 2.0 - y * scale
		);
	}

	/**
	 * Get the width of the graph
	 * @return the total width of the graph
	 */
	private double getGraphWidth() {
		return getWidth() / scale;
	}

	/**
	 * Get the height of the graph
	 * @return the total height of the graph
	 */
	private double getGraphHeight() {
		return getHeight() / scale;
	}

	/**
	 * Draw the grid lines and numbers
	 * @param g the {@link Graphics} context to draw on
	 */
	private void drawScales(Graphics g) {
		/* vertical lines */
		for (int x = (int) -(getGraphWidth() / 2); x <= getGraphWidth() / 2; x++) {
			Point cc = graphToPanelCoords(x, 0);
			if (x == 0) {
				((Graphics2D) g).setStroke(new BasicStroke(2));
				g.setColor(fgcolor.darker());
			} else {
				((Graphics2D) g).setStroke(new BasicStroke(1));
				g.setColor(fgcolor.darker());
				g.drawString("" + x, (int) Math.round(cc.x - (x < 0 ? 4 : 2)), (int) Math.round(cc.y + 15));
				g.setColor(fgcolor.darker().darker());
			}
			g.drawLine((int) Math.round(cc.x), 0, (int) Math.round(cc.x), getHeight());
		}

		/* horizontal lines */
		for (int y = (int) -(getGraphHeight() / 2); y <= getGraphHeight() / 2; y++) {
			Point cc = graphToPanelCoords(0, y);
			if (y == 0) {
				((Graphics2D) g).setStroke(new BasicStroke(2));
				g.setColor(fgcolor.darker());
			} else {
				((Graphics2D) g).setStroke(new BasicStroke(1));
				g.setColor(fgcolor.darker());
				g.drawString("" + y, (int) cc.x + 5, (int) cc.y + 3);
				g.setColor(fgcolor.darker().darker());
			}
			g.drawLine(0, (int) Math.round(cc.y), getWidth(), (int) Math.round(cc.y));
		}

		/* (0, 0) label */
		g.setColor(fgcolor.darker());
		g.drawString("0", getWidth() / 2 + 5, getHeight() / 2 + 15);
	}

	/**
	 * Draw the graph of the equation
	 * @param g the {@link Graphics} context to draw on
	 */
	private void drawGraph(Graphics g) {
		double x, y;
		Point prev;

		g.setColor(fgcolor);
		((Graphics2D) g).setStroke(new BasicStroke(3));
		x = -(getGraphWidth() / 2);
		y = e.evaluate(x);
		prev = graphToPanelCoords(x, y);
		for (x = -(getGraphWidth() / 2); x < getGraphWidth() / 2; x += levelOfDetail) {
			y = e.evaluate(x);
			Point d = graphToPanelCoords(x, y);
			g.drawLine((int) Math.round(prev.x), (int) Math.round(prev.y), (int) Math.round(d.x), (int) Math.round(d.y));
			prev = d;
		}
		((Graphics2D) g).setStroke(new BasicStroke(1));
	}

	/**
	 * Draw a tooltip at the last received mouse click/drag position.
	 * Tooltip contains a dot on the line and nearby text with the value of
	 * the coordinates where the mouse was pressed.
	 * @param g the {@link Graphics} context to draw on
	 */
	private void drawTooltip(Graphics g) {
		Point gc = panelToGraphCoords(tooltipMousePosition.x, 0);
		gc.y = e.evaluate(gc.x);
		Point pc = graphToPanelCoords(gc.x, gc.y);

		g.setColor(Color.GRAY);
		g.fillOval((int) Math.round(pc.x) - 6, (int) Math.round(pc.y) - 6, 12, 12);
		g.setColor(fgcolor);
		g.drawString(String.format("x: %.4f", gc.x), (int) pc.x + 15, (int) pc.y + 15);
		g.drawString(String.format("y: %.4f", gc.y), (int) pc.x + 15, (int) pc.y + 27);
	}

	/**
	 * Paint the graph panel.
	 * Paints the scales, graph, and if necessary, the tooltip.
	 * @param g the {@link Graphics} context to draw on
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		((Graphics2D) g).setBackground(getBackground());
		g.clearRect(0, 0, getWidth(), getHeight());

		drawScales(g);
		drawGraph(g);
		if (tooltipEnabled)
			drawTooltip(g);
	}

	/**
	 * Handle the mouse button being pressed.
	 * Enables the tooltip at the mouse position.
	 * @param ev information about the mouse press event
	 */
	@Override
	public void mousePressed(MouseEvent ev) {
		tooltipMousePosition = new Point(ev.getX(), ev.getY());
		tooltipEnabled = true;
		repaint();
	}

	/**
	 * Handle the mouse button being released.
	 * Disables the tooltip.
	 * @param ev information about the mouse release event
	 */
	@Override
	public void mouseReleased(MouseEvent ev) {
		tooltipEnabled = false;
		repaint();
	}

	/**
	 * Handle the mouse being dragged.
	 * Enables the tooltip at the mouse position.
	 * @param ev information about the mouse drag event
	 */
	@Override
	public void mouseDragged(MouseEvent ev) {
		tooltipMousePosition = new Point(ev.getX(), ev.getY());
		tooltipEnabled = true;
		repaint();
	}

	/**
	 * Handle the mouse being scrolled.
	 * Zooms in/out appropriately.
	 * @param ev information about the mouse wheel event
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent ev) {
		/* getPreciseWheelRotation() returns negative values for
		 * scrolling the wheel up and positive values for scrolling the
		 * wheel down */
		/* curve for input sensitivity is scroll ^ -(scale - 1) */
		if (ev.getPreciseWheelRotation() < 0)
			scale += Math.pow(-ev.getPreciseWheelRotation(), (-(scale-1)));
		else
			scale -= Math.pow(ev.getPreciseWheelRotation(), (-(scale-1)));

		if (scale < MIN_SCALE)
			scale = MIN_SCALE;
		repaint();
	}

	/* functions below are unused, but must be defined in order to
	 * implement event handler interfaces */

	@Override
	public void mouseMoved(MouseEvent ev) {
	}

	@Override
	public void mouseClicked(MouseEvent ev) {
	}

	@Override
	public void mouseEntered(MouseEvent ev) {
	}

	@Override
	public void mouseExited(MouseEvent ev) {
	}
}
