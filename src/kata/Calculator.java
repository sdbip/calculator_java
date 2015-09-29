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
		double operand0 = this.value;
		this.value = value;
		deferredOperator = x -> operand0 + x;
	}

	public void multiply(double value) {
		this.value *= value;
	}

	public void subtract(double value) {
		double operand0 = this.value;
		this.value = value;
		deferredOperator = x -> operand0 - x;
	}

	public void divide(double value) {
		this.value /= value;
	}
}
