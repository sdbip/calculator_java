package kata;

public class Calculator {
	static final Operator IDENTITY_OPERATOR = operand -> operand;

	private Operator deferredOperator = IDENTITY_OPERATOR;
	private double value = 0.0;

	public void enter(double operand) {
		value = operand;
	}

	public double getResult() {
		value = deferredOperator.call(value);
		deferredOperator = IDENTITY_OPERATOR;
		return value;
	}

	public void add(double term) {
		double operand = value;
		value = deferredOperator.call(term);
		deferredOperator = x -> operand + x;
	}

	public void multiply(double factor) {
		value *= factor;
	}

	public void subtract(double term) {
		add(-term);
	}

	public void divide(double quotient) {
		value /= quotient;
	}
}
