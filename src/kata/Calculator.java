package kata;

public class Calculator {
	private double value;

	public void enter(double value) {

		this.value = value;
	}

	public double getValue() {
		return value;
	}

	public void add(double value) {
		this.value += value;
	}

	public void multiply(double value) {
		this.value *= value;
	}

	public void subtract(double value) {
		this.value -= value;
	}

	public void divide(double value) {
		this.value /= value;
	}
}
