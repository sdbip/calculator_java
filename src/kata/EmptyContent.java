package kata;

public class EmptyContent implements Content {
	public Content append(String digit) {
		return new ContentImpl(digit);
	}

	public String getDisplayedValue(double value, DisplayFormatter displayFormatter) {
		return displayFormatter.format(value);
	}

	public String get() {
		return "0";
	}

	public boolean isEmpty() {
		return true;
	}
}
