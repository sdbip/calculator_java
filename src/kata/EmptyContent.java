package kata;

public class EmptyContent implements Content {
	public Content append(String digit) {
		return new ContentImpl(digit);
	}

	public String get() {
		return null;
	}

	public boolean isEmpty() {
		return true;
	}
}
