package kata;

public interface Content {
	EmptyContent EMPTY = new EmptyContent();

	Content append(String digit);

	String getDisplayedValue(double value, DisplayFormatter displayFormatter);
	String get();

	boolean isEmpty();
}
