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

	@Test
	public void canSubtract() {
		pushDigits('3');
		calculator.pushMinus();
		pushDigits('2');
		calculator.calculate();
		assertEquals("1", calculator.display());
	}

	@Test
	public void canMultiply() {
		pushDigits('3');
		calculator.pushTimes();
		pushDigits('2');
		calculator.calculate();
		assertEquals("6", calculator.display());
	}

	@Test
	public void canDivide() {
		pushDigits('6');
		calculator.pushDivide();
		pushDigits('2');
		calculator.calculate();
		assertEquals("3", calculator.display());
	}

	@Test
	public void canAddRepeatedly() {
		pushDigits('1');
		calculator.pushPlus();
		pushDigits('2');
		calculator.pushPlus();
		pushDigits('2');
		calculator.calculate();
		assertEquals("5", calculator.display());
	}

	@Test
	public void canSubtractRepeatedly() {
		pushDigits('5');
		calculator.pushMinus();
		pushDigits('2');
		calculator.pushMinus();
		pushDigits('2');
		calculator.calculate();
		assertEquals("1", calculator.display());
	}

	@Test
	public void canMultiplyRepeatedly() {
		pushDigits('2');
		calculator.pushTimes();
		pushDigits('3');
		calculator.pushTimes();
		pushDigits('5');
		calculator.calculate();
		assertEquals("30", calculator.display());
	}

	@Test
	public void canDivideRepeatedly() {
		pushDigits('3', '0');
		calculator.pushDivide();
		pushDigits('2');
		calculator.pushDivide();
		pushDigits('5');
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
