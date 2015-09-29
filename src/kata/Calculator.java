package kata;

public class Calculator {
	static final Operator IDENTITY_OPERATOR = lhs -> lhs;

	private Operator deferredOperator = IDENTITY_OPERATOR;
	private double value = 0.0;

	public void enter(double value) {
		this.value = value;
	}

	public double calculate() {
		return deferredOperator.call(value);
	}

	public void add(double value) {
		double operand = this.value;
		this.value = deferredOperator.call(value);
		deferredOperator = x -> operand + x;
	}

	public void multiply(double value) {
		this.value *= value;
	}

	public void subtract(double value) {
		add(-value);
	}

	public void divide(double value) {
		this.value /= value;
	}
}
