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
		calculator.displayBuffer.setLocale(Locale.US);
	}

	@Test
	public void canEnterDigits() {
		enterDigits('1', '2');
		assertEquals("12", calculator.display());
	}

	@Test
	public void canEnterDecimal() {
		enterDigits('1');
		calculator.enterDecimalSeparator();
		enterDigits('1');
		assertEquals("1.1", calculator.display());
	}

	@Test
	public void canOnlyEnterOneDecimal() {
		enterDigits('1');
		calculator.enterDecimalSeparator();
		enterDigits('1');
		calculator.enterDecimalSeparator();
		assertEquals("1.1", calculator.display());
	}

	@Test
	public void addsZeroWhenPressingDecimalPointer() {
		calculator.enterDecimalSeparator();
		enterDigits('1');
		assertEquals("0.1", calculator.display());
	}

	@Test
	public void addsZeroWhenPressingDecimalPointer_localized() {
		calculator.displayBuffer.setLocale(Locale.FRANCE);
		calculator.enterDecimalSeparator();
		enterDigits('1');
		assertEquals("0,1", calculator.display());
	}


	@Test
	public void honorsLocale() {
		calculator.displayBuffer.setLocale(Locale.FRANCE);
		enterDigits('1');
		calculator.enterDecimalSeparator();
		enterDigits('1');
		calculator.enterDecimalSeparator();
		assertEquals("1,1", calculator.display());
	}


	@Test
	public void clearsBufferWhenCalculating() {
		enter("12");
		calculator.evaluate();
		enter("3");
		assertEquals("3", calculator.display());
	}

	@Test
	public void canAdd() {
		enter("12");
		calculator.pushAddition();
		enter("3");
		calculator.evaluate();
		assertEquals("15", calculator.display());
	}

	@Test
	public void canSubtract() {
		enter("12");
		calculator.pushSubtraction();
		enter("3");
		calculator.evaluate();
		assertEquals("9", calculator.display());
	}

	@Test
	public void canDivide() {
		enter("12");
		calculator.pushDivision();
		enter("3");
		calculator.evaluate();
		assertEquals("4", calculator.display());
	}

	@Test
	public void canMultiply() {
		enter("12");
		calculator.pushMultiplication();
		enter("3");
		calculator.evaluate();
		assertEquals("36", calculator.display());
	}

	@Test
	public void multipliesBeforeAdds() {
		enter("1");
		calculator.pushAddition();
		enter("2");
		calculator.pushMultiplication();
		enter("3");
		calculator.evaluate();
		assertEquals("7", calculator.display());
	}

	@Test
	public void dividesBeforeSubtracts() {
		enter("11");
		calculator.pushSubtraction();
		enter("4");
		calculator.pushDivision();
		enter("2");
		calculator.evaluate();
		assertEquals("9", calculator.display());
	}


	@Test
	public void honorsLocaleInCalculations() {
		calculator.displayBuffer.setLocale(Locale.FRANCE);
		enter("2");
		calculator.enterDecimalSeparator();
		enter("1");
		calculator.evaluate();
		assertEquals("2,1", calculator.display());
	}


	private void enter(String digits) {
		enterDigits(digits.toCharArray());
	}

	private void enterDigits(char... digits) {
		for (char c : digits) {
			calculator.enterDigit(c);
		}
	}

}

// Satvi