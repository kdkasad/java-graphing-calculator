import javax.swing.JOptionPane;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException;

/**
 * Represents an equation
 * @author Kian Kasad
 * @version 2021-05-17
 */
public class Equation {
	private Expression e;

	/**
	 * Create an equation
	 * @param equationString the equation typed as a string
	 * TODO: add docs about proper syntax
	 */
	public Equation(String equationString) {
		/* strip leading 'y = ' */
		equationString = equationString.replaceAll("^\\s*y\\s*=\\s*", "");
		try {
			e = new ExpressionBuilder(equationString).variable("x").build();
		} catch (UnknownFunctionOrVariableException exception) {
			JOptionPane.showMessageDialog(null, exception.getMessage(), "Invalid equation", JOptionPane.ERROR_MESSAGE);
		} catch (IllegalArgumentException exception) {
			JOptionPane.showMessageDialog(null, exception.getMessage(), "Invalid equation", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Find the value of 'y' at the given value for 'x'
	 * @param x the value of 'x'
	 * @return the value of 'y' at the given 'x'
	 */
	public double evaluate(double x) {
		try {
			return e.setVariable("x", x).evaluate();
		} catch (ArithmeticException e) {
			return Double.POSITIVE_INFINITY;
		}
	}
}
