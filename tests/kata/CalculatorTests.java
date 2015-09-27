package kata;

import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class CalculatorTests {

	private Calculator calculator;

	@Before
	public void setUp() throws Exception {
		calculator = new Calculator();
		calculator.buffer.setLocale(Locale.US);
	}

	@Test
	public void pressedDigitIsDisplayed() {
		calculator.enterDigit("8"); // GUI identifies button by its label
		assertEquals("8", calculator.getDisplay());
	}

	@Test
	public void severalPressedDigitsAreDisplayedInOrder() {
		calculator.enterDigit("2");
		calculator.enterDigit("8");
		assertEquals("28", calculator.getDisplay());
	}

	@Test
	public void decimalPointerIsAddedToDisplay() {
		calculator.enterDigit("2");
		calculator.enterDecimalPointer();
		calculator.enterDigit("8");
		assertEquals("2.8", calculator.getDisplay());
	}

	@Test
	public void decimalPointerHonorsLocale() {
		calculator.buffer.setLocale(Locale.FRANCE);
		calculator.enterDigit("2");
		calculator.enterDecimalPointer();
		calculator.enterDigit("8");
		assertEquals("2,8", calculator.getDisplay());
	}

	@Test
	public void calculateHonorsLocale() {
		calculator.buffer.setLocale(Locale.FRANCE);
		calculator.enterDigit("2");
		calculator.enterDecimalPointer();
		calculator.enterDigit("8");
		calculator.calculate();
		assertEquals("2,8", calculator.getDisplay());
	}

	@Test
	public void decimalPointWhenEmptyAddsZero() {
		calculator.enterDecimalPointer();
		calculator.enterDigit("2");
		assertEquals("0.2", calculator.getDisplay());
	}

	@Test
	public void equalsClearsBuffer() {
		calculator.enterDigit("1");
		calculator.calculate();
		calculator.enterDigit("1");
		assertEquals("1", calculator.getDisplay());
	}

	@Test
	public void equalsDoesNotClearDisplay() {
		calculator.enterDigit("1");
		calculator.calculate();
		assertEquals("1", calculator.getDisplay());
	}

	@Test
	public void plusAddsToTheCurrentValue() {
		calculator.enterDigit("1");
		calculator.pressOperator("+");
		calculator.enterDigit("1");
		calculator.calculate();
		assertEquals("2", calculator.getDisplay());
	}

	@Test
	public void timesMultipliesAgainstTheCurrentValue() {
		calculator.enterDigit("3");
		calculator.pressOperator("×");
		calculator.enterDigit("2");
		calculator.calculate();
		assertEquals("6", calculator.getDisplay());
	}

	@Test
	public void minusSubtractsFromTheCurrentValue() {
		calculator.enterDigit("1");
		calculator.pressOperator("−");
		calculator.enterDigit("1");
		calculator.calculate();
		assertEquals("0", calculator.getDisplay());
	}

	@Test
	public void divisionDividesAgainstTheCurrentValue() {
		calculator.enterDigit("3");
		calculator.pressOperator("÷");
		calculator.enterDigit("2");
		calculator.calculate();
		assertEquals("1.5", calculator.getDisplay());
	}

	@Test
	public void multiplicationPrecedesAddition() {
		calculator.enterDigit("1");
		calculator.pressOperator("+");
		calculator.enterDigit("2");
		calculator.pressOperator("×");
		calculator.enterDigit("3");
		calculator.calculate();
		assertEquals("7", calculator.getDisplay());
	}

	@Test
	public void divisionPrecedesSubtraction() {
		calculator.enterDigit("4");
		calculator.pressOperator("-");
		calculator.enterDigit("4");
		calculator.pressOperator("÷");
		calculator.enterDigit("2");
		calculator.calculate();
		assertEquals("2", calculator.getDisplay());
	}

	@Test
	public void precedentOperatorsAreNotFlipped() {
		calculator.enterDigit("2");
		calculator.pressOperator("÷");
		calculator.enterDigit("2");
		calculator.pressOperator("×");
		calculator.enterDigit("2");
		calculator.calculate();
		assertEquals("2", calculator.getDisplay());
	}

}
