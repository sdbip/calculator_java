package kata;

public interface Content {
	EmptyContent EMPTY = new EmptyContent();

	Content append(String digit);

	String get();

	boolean isEmpty();
}
