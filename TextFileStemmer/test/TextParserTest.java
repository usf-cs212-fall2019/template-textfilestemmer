import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@SuppressWarnings("javadoc")
public class TextParserTest {

	// Right-click a nested class to run the tests in that nested class only.

	@Nested
	@TestMethodOrder(OrderAnnotation.class)
	public class CleanTestCases {

		// This is the code each test is running within this nested class.

		public void test(String text, String expected) {
			String actual = TextParser.clean(text);
			assertEquals(expected, actual);
		}

		// Right-click individual test methods to run only that test.

		@Test
		@Order(1)
		public void helloWorld() {
			test("hello world", "hello world");
		}

		@Test
		@Order(2)
		public void withTabs() {
			test("\t hello  world ", "\t hello  world ");
		};

		@Test
		@Order(3)
		public void withPunctuation() {
			test("hello, world!", "hello world");
		}

		@Test
		@Order(4)
		public void withDigit() {
			test("hello 1 world", "hello  world");
		}

		@Test
		@Order(5)
		public void withSymbol() {
			test("hello @world", "hello world");
		}

		@Test
		@Order(6)
		public void withUppercase() {
			test("HELLO WORLD", "hello world");
		}

		@Test
		@Order(7)
		public void withExclamation() {
			test("¡Hello world!", "hello world");
		}

		@Test
		@Order(8)
		public void withMarks() {
			test("héḶlõ ẁörld", "hello world");
		}

		@Test
		@Order(9)
		public void onlySpaces() {
			test("   ", "   ");
		}

		@Test
		@Order(10)
		public void onlyDigits() {
			test("1234567890", "");
		}
	}

	@Nested
	@TestMethodOrder(OrderAnnotation.class)
	public class ParseTextTests {

		public void test(String text) {
			String[] expected = new String[] {"hello", "world"};
			assertArrayEquals(expected, TextParser.parse(text));
		}

		@Test
		@Order(1)
		public void helloWorld() {
			test("hello world");
		}

		@Test
		@Order(2)
		public void withTabs() {
			test("\t hello  world ");
		}

		@Test
		@Order(3)
		public void withPunctuation() {
			test("hello, world!");
		}

		@Test
		@Order(4)
		public void withDigit() {
			test("hello 1 world");
		}

		@Test
		@Order(5)
		public void withSymbol() {
			test("hello @world");
		}

		@Test
		@Order(6)
		public void withUppercase() {
			test("HELLO WORLD");
		}

		@Test
		@Order(7)
		public void withExclamation() {
			test("¡Hello world!");
		}

		@Test
		@Order(8)
		public void withMarks() {
			test("héḶlõ ẁörld");
		}
	}

	@Nested
	@TestMethodOrder(OrderAnnotation.class)
	public class ParseTextEmptyTests {

		public void test(String text) {
			String[] expected = new String[0];
			assertArrayEquals(expected, TextParser.parse(text));
		}

		@Test
		@Order(1)
		public void singleSpace() {
			test(" ");
		}

		@Test
		@Order(2)
		public void emptyString() {
			test("");
		}

		@Test
		@Order(3)
		public void onlyDigits() {
			test("1234567890");
		}

		@Test
		@Order(4)
		public void mixedSymbols() {
			test("\t 11@ ");
		}
	}
}
