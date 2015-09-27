package kata;

class Content {
	String buffer;

	public Content(String buffer) {
		this.buffer = buffer;
	}

	public Content(String buffer, String alternate) {
		this(buffer);
		if (isEmpty()) this.buffer = alternate;
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

	private boolean isEmpty() {
		return buffer == null;
	}
}
