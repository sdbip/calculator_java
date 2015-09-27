package kata;

public interface Content {
	static Content empty() {
		return new EmptyContent();
	}

	Content append(String digit);

	@Override
	String toString();

	String getDisplayedValue(double value, DisplayFormatter displayFormatter);

	boolean isEmpty();

}
