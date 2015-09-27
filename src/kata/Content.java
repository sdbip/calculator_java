package kata;

public interface Content {
	static Content empty() {
		return new ContentImpl(null);
	}

	Content append(String digit);

	@Override
	String toString();

	String getDisplayedValue(double value, DisplayFormatter displayFormatter);

	boolean isEmpty();
}
