package kata;

class Content {
	String buffer;

	public Content(String buffer) {
		this.buffer = buffer;
	}

	static Content empty() {
		return new Content(null);
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
