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
		calculator.setLocale(Locale.US);
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
		calculator.setLocale(Locale.FRANCE);
		calculator.enterDigit("2");
		calculator.enterDecimalPointer();
		calculator.enterDigit("8");
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
		calculator.pressPlus();
		calculator.enterDigit("1");
		calculator.calculate();
		assertEquals("2", calculator.getDisplay());
	}

	@Test
	public void timesMultipliesAgainstTheCurrentValue() {
		calculator.enterDigit("3");
		calculator.pressTimes();
		calculator.enterDigit("2");
		calculator.calculate();
		assertEquals("6", calculator.getDisplay());
	}

}
