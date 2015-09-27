package kata;

class ContentImpl implements Content {
	final String buffer;

	public ContentImpl(String buffer) {
		this.buffer = buffer;
	}

	public Content append(String digit) {
		return new ContentImpl(buffer + digit);
	}
	public Content append(char decimalSeparator) {
		return new ContentImpl(buffer + decimalSeparator);
	}

	public String get() {
		return buffer;
	}

	public boolean isEmpty() {
		return false;
	}
}
