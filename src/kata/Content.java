package kata;

public interface Content {
	static Content empty() {
		return new ContentImpl(null);
	}

	void append(String digit);

	@Override
	String toString();

	String getDisplayedValue(double value, DisplayFormatter displayFormatter);

	boolean isEmpty();
}
