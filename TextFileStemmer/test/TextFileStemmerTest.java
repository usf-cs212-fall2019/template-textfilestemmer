import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@SuppressWarnings("javadoc")
public class TextFileStemmerTest {

	// Right-click a nested class to run the tests in that nested class only.

	@Nested
	@TestMethodOrder(OrderAnnotation.class)
	public class StemLineTests {

		// Test cases from: http://snowballstem.org/algorithms/english/stemmer.html
		// Right-click individual test methods to run only that test.

		@Test
		@Order(1)
		public void testOneWord() {
			String line = "conspicuously";
			String[] output = { "conspicu" };

			Set<String> expected = Set.of(output);
			Set<String> actual = TextFileStemmer.uniqueStems(line);

			assertEquals(expected, actual);
		}

		@Test
		@Order(2)
		public void testEmpty() {
			String line = "";

			Set<String> expected = Collections.emptySet();
			Set<String> actual = TextFileStemmer.uniqueStems(line);

			assertEquals(expected, actual);
		}

		@Test
		@Order(3)
		public void testGroupOne() {
			String[] input = { "consign", "consigned", "consigning", "consignment",
					"consist", "consisted", "consistency", "consistent", "consistently",
					"consisting", "consists", "consolation", "consolations",
					"consolatory", "console", "consoled", "consoles", "consolidate",
					"consolidated", "consolidating", "consoling", "consolingly",
					"consols", "consonant", "consort", "consorted", "consorting",
					"conspicuous", "conspicuously", "conspiracy", "conspirator",
					"conspirators", "conspire", "conspired", "conspiring", "constable",
					"constables", "constance", "constancy", "constant" };

			String[] output = { "consign", "consign", "consign", "consign", "consist",
					"consist", "consist", "consist", "consist", "consist", "consist",
					"consol", "consol", "consolatori", "consol", "consol", "consol",
					"consolid", "consolid", "consolid", "consol", "consol", "consol",
					"conson", "consort", "consort", "consort", "conspicu", "conspicu",
					"conspiraci", "conspir", "conspir", "conspir", "conspir", "conspir",
					"constabl", "constabl", "constanc", "constanc", "constant" };

			String line = String.join(", ", input);
			Set<String> expected = Arrays.stream(output).collect(Collectors.toSet());
			Set<String> actual = TextFileStemmer.uniqueStems(line);

			assertEquals(expected, actual);
		}

		@Test
		@Order(4)
		public void testGroupTwo() {
			String[] input = { "KNACK", "KNACKERIES", "KNACKS", "KNAG", "KNAVE",
					"KNAVES", "KNAVISH", "KNEADED", "KNEADING", "KNEE", "KNEEL",
					"KNEELED", "KNEELING", "KNEELS", "KNEES", "KNELL", "KNELT", "KNEW",
					"KNICK", "KNIF", "KNIFE", "KNIGHT", "KNIGHTLY", "KNIGHTS", "KNIT",
					"KNITS", "KNITTED", "KNITTING", "KNIVES", "KNOB", "KNOBS", "KNOCK",
					"KNOCKED", "KNOCKER", "KNOCKERS", "KNOCKING", "KNOCKS", "KNOPP",
					"KNOT", "KNOTS" };

			String[] output = { "knack", "knackeri", "knack", "knag", "knave",
					"knave", "knavish", "knead", "knead", "knee", "kneel", "kneel",
					"kneel", "kneel", "knee", "knell", "knelt", "knew", "knick", "knif",
					"knife", "knight", "knight", "knight", "knit", "knit", "knit", "knit",
					"knive", "knob", "knob", "knock", "knock", "knocker", "knocker",
					"knock", "knock", "knopp", "knot", "knot" };

			String line = String.join(" **** ", input);
			Set<String> expected = Arrays.stream(output).collect(Collectors.toSet());
			Set<String> actual = TextFileStemmer.uniqueStems(line);

			assertEquals(expected, actual);
		}
	}

	@Nested
	@TestMethodOrder(OrderAnnotation.class)
	public class StemFileTests {

		@Test
		@Order(1)
		public void testWords() throws IOException {
			Path inputPath = Paths.get("test", "words.tExT");

			Set<String> actual = TextFileStemmer.uniqueStems(inputPath);
			Set<String> expected = Set.of("observ", "observa", "observacion", "perfor",
					"perforc", "perform");

			assertEquals(expected, actual);
		}

		@Test
		@Order(2)
		public void testSymbols() throws IOException {
			Path inputPath = Paths.get("test", "symbols.txt");

			Set<String> actual = TextFileStemmer.uniqueStems(inputPath);
			Set<String> expected = Set.of("antelop");

			assertEquals(expected, actual);
		}

		@Test
		@Order(3)
		public void testAnimals() throws IOException {
			Path inputPath = Paths.get("test", "animals.text");

			Set<String> actual = TextFileStemmer.uniqueStems(inputPath);
			Set<String> expected = Set.of("axolotl", "echidna", "lori", "mongoos",
					"narwhal", "okapi", "platypus", "tarsier");

			assertEquals(expected, actual);
		}
	}
}
