package kata;

public interface Content {
	EmptyContent EMPTY = new EmptyContent();

	Content append(String digit);

	@Override
	String toString();

	String getDisplayedValue(double value, DisplayFormatter displayFormatter);

	boolean isEmpty();

}
