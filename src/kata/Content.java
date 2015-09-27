package kata;

public interface Content {
	Content EMPTY = new EmptyContent();

	Content append(String digit);
	Content append(char decimalSeparator);

	String get();

}
