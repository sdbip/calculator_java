package kata;

class ContentImpl implements Content {
	String buffer;

	public ContentImpl(String buffer) {
		this.buffer = buffer;
	}

	public void append(String digit) {
		buffer = !isEmpty() ? buffer + digit : digit;
	}

	public String toString() {
		return buffer;
	}

	public String getDisplayedValue(double value, DisplayFormatter displayFormatter) {
		return isEmpty() ? displayFormatter.format(value) : buffer;
	}

	public boolean isEmpty() {
		return buffer == null;
	}
}
