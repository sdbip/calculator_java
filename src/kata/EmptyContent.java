package kata;

public class EmptyContent implements Content {
	public Content append(String digit) {
		return new ContentImpl(digit);
	}
	public Content append(char decimalSeparator) {
		return new ContentImpl("0" + decimalSeparator);
	}

	public String get() {
		return null;
	}

	public boolean hasValue() {
		return false;
	}

	public double getValue(DisplayFormatter formatter) {
		return 0;
	}

}
