package kata;

public class EmptyContent implements Content {
	public Content append(String digit) {
		return new ContentImpl(digit);
	}

	public String getDisplayedValue(double value, DisplayFormatter displayFormatter) {
		return displayFormatter.format(value);
	}

	public boolean isEmpty() {
		return true;
	}
}
