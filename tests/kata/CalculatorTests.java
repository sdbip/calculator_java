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
	}

	@Test
	public void canEnterDigits() {
		calculator.enterDigit('1');
		calculator.enterDigit('2');
		assertEquals("12", calculator.display());
	}

	@Test
	public void canEnterDecimal() {
		calculator.enterDigit('1');
		calculator.enterDecimalPointer();
		calculator.enterDigit('1');
		assertEquals("1.1", calculator.display());
	}

	@Test
	public void canOnlyEnterOneDecimal() {
		calculator.enterDigit('1');
		calculator.enterDecimalPointer();
		calculator.enterDecimalPointer();
		calculator.enterDigit('1');
		assertEquals("1.1", calculator.display());
	}

	@Test
	public void honorsLocale() {
		calculator.setLocale(Locale.FRANCE);

		calculator.enterDigit('1');
		calculator.enterDecimalPointer();
		calculator.enterDigit('1');
		assertEquals("1,1", calculator.display());
	}

}
