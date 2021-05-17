import net.objecthunter.exp4j.*;

public class Equation {
	private Expression e;

	public Equation(String equationString) {
		/* strip leading 'y = ' */
		equationString = equationString.replaceAll("^\\s*y\\s*=\\s*", "");
		e = new ExpressionBuilder(equationString).variable("x").build();
	}

	public double evaluate(double x) {
		return e.setVariable("x", x).evaluate();
	}
}
