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
	public void canEnter1() {
		pushDigits('1');
		assertEquals("1", calculator.display());
	}

	@Test
	public void canEnter23() {
		pushDigits('2', '3');
		assertEquals("23", calculator.display());
	}

	@Test
	public void canEnterDecimalValues() {
		pushDigits('1', '3', '.', '3');
		assertEquals("13.3", calculator.display());
	}

	@Test
	public void ignoresSecondDecimalPoint() {
		pushDigits('1', '3', '.', '.', '3');
		assertEquals("13.3", calculator.display());
	}

	@Test
	public void honorsLocale() {
		calculator.setLocale(Locale.FRANCE);
		pushDigits('1', '3', '.', '.', '3');
		assertEquals("13,3", calculator.display());
	}

	@Test
	public void canAdd() {
		pushDigits('1');
		calculator.pushPlus();
		pushDigits('2');
		calculator.calculate();
		assertEquals("3", calculator.display());
	}


	private void pushDigits(char... digits) {
		for (char digit : digits) {
			if (digit == '.')
				calculator.pushDecimalPoint();
			else
				calculator.pushDigit(digit);
		}
	}

}
