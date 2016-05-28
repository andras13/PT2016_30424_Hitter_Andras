package vocabulary;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author ST
 *
 */
public class Tests {

	private static Dictionary d = new Dictionary();
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test method for {@link vocabulary.Dictionary#addWord(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddWord() {
		d.addWord("calm", "quiet,peaceful");
		assertEquals(d.getMap().get("calm"),"quiet,peaceful");
	}

	/**
	 * Test method for {@link vocabulary.Dictionary#removeWord(java.lang.String)}.
	 */
	@Test
	public void testRemoveWord() {
		d.removeWord("calm");
	}

}
