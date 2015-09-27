package kata;

class ContentImpl {
	String buffer;

	public ContentImpl(String buffer) {
		this.buffer = buffer;
	}

	static ContentImpl empty() {
		return new ContentImpl(null);
	}

	public void append(String digit) {
		buffer = !isEmpty() ? buffer + digit : digit;
	}

	@Override
	public String toString() {
		return buffer;
	}

	public String getDisplayedValue(double value, DisplayFormatter displayFormatter) {
		return isEmpty() ? displayFormatter.format(value) : buffer;
	}

	boolean isEmpty() {
		return buffer == null;
	}
}
