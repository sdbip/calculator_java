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
		calculator.displayFormatter.setLocale(Locale.US);
	}

	@Test
	public void canEnterDigits() {
		enter("12");
		assertEquals("12", calculator.display());
	}

	@Test
	public void canEnterDecimal() {
		enter("1.1");
		assertEquals("1.1", calculator.display());
	}

	@Test
	public void canOnlyEnterOneDecimal() {
		enter("1..1");
		assertEquals("1.1", calculator.display());
	}

	@Test
	public void honorsLocaleWhenEnteringNumbers() {
		calculator.displayFormatter.setLocale(Locale.FRANCE);

		enter("1.1");
		assertEquals("1,1", calculator.display());
	}

	private void enter(String value) {
		for (char c : value.toCharArray()) {
			if (Character.isDigit(c))
				calculator.enterDigit(c);
			else
				calculator.enterDecimalPointer();
		}
	}

	@Test
	public void canAdd() {
		enter("1.1");
		calculator.add();
		enter("2");
		calculator.evaluate();
		assertEquals("3.1", calculator.display());
	}

	@Test
	public void canAddRepeatedly() {
		enter("1");
		calculator.add();
		enter("2");
		calculator.add();
		enter("2");
		calculator.evaluate();
		assertEquals("5", calculator.display());
	}

	@Test
	public void honorsLocaleWhenAdding() {
		calculator.displayFormatter.setLocale(Locale.FRANCE);

		enter("1.1");
		calculator.add();
		enter("2");
		calculator.evaluate();
		assertEquals("3,1", calculator.display());
	}

}
