package kata;

public class Calculator {
	private String buffer = "";

	public void enterDigit(char c) {
		buffer += c;
	}

	public String display() {
		return buffer;
	}

	public void enterDecimalPointer() {
		buffer += '.';
	}
}
