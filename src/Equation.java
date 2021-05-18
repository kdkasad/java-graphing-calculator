import net.objecthunter.exp4j.*;

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
		e = new ExpressionBuilder(equationString).variable("x").build();
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
