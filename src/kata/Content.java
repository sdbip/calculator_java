package kata;

public interface Content {
	EmptyContent EMPTY = new EmptyContent();

	Content append(String digit);
	Content append(char decimalSeparator);

	String get();

	boolean isEmpty();

}
