package kata;

public class Calculator {
	public String display = "";

	public void enterDigit(String digit) {
		display += digit;
	}

	public void enterDecimalPointer() {
		display += ".";
	}
}
