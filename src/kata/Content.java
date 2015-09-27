package kata;

public interface Content {
	static Content empty() {
		return new Content() {
			public Content append(String digit) {
				return new ContentImpl(digit);
			}

			public String getDisplayedValue(double value, DisplayFormatter displayFormatter) {
				return displayFormatter.format(value);
			}

			public boolean isEmpty() {
				return true;
			}
		};
	}

	Content append(String digit);

	@Override
	String toString();

	String getDisplayedValue(double value, DisplayFormatter displayFormatter);

	boolean isEmpty();
}
