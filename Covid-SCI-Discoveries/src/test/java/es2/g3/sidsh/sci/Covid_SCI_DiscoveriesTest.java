/**
 * 
 */
package es2.g3.sidsh.sci;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author dariop
 *
 */
class Covid_SCI_DiscoveriesTest {
	
	Covid_SCI_Discoveries covid = new Covid_SCI_Discoveries("");

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
	
	/**
	 * Test method for {@link es2.g3.sidsh.sci.Covid_SCI_DiscoveriesTest#getPdfFiles()}.
	 */
	@Test
	void testGetPdfFiles() {
		assertThrows(NullPointerException.class, () -> covid.getPdfFiles());
	}

	/**
	 * Test method for {@link es2.g3.sidsh.sci.Covid_SCI_DiscoveriesTest#notYetExtracted(java.io.File)}.
	 */
	@Test
	void testNotYetExtracted() {
		assertTrue(covid.notYetExtracted(new File(" "), false));
	}

	/**
	 * Test method for {@link es2.g3.sidsh.sci.Covid_SCI_DiscoveriesTest#isPdf(java.io.File)}.
	 */
	@Test
	void testIsPdf() {
		File f = new File("a.pdf");
		assertTrue(covid.isPdf(f));
	}

	/**
	 * Test method for {@link es2.g3.sidsh.sci.Covid_SCI_DiscoveriesTest#extractXPathValue(org.jdom.Document, java.lang.String)}.
	 *
	 */
	@Test
	void testExtractXPathValue() {
		assertThrows(JDOMException.class, () -> Covid_SCI_Discoveries.extractXPathValue(new Document(), ""));
	}

	/**
	 * Test method for {@link es2.g3.sidsh.sci.Covid_SCI_DiscoveriesTest#extractAuthors(org.jdom.Document, java.lang.String)}.
	 * 
	 */
	@Test
	void testExtractAuthors(){
		assertThrows(JDOMException.class, () -> Covid_SCI_Discoveries.extractAuthors(new Document(),""));
	}

	/**
	 * Test method for {@link es2.g3.sidsh.sci.Covid_SCI_DiscoveriesTest#extractNLM(org.jdom.Document)}.
	 */
	@Test
	void testExtractNLM() {
		assertThrows(IllegalStateException.class, () -> covid.extractNLM(new Document()));	}

	/**
	 * Test method for {@link es2.g3.sidsh.sci.Covid_SCI_DiscoveriesTest#generateArticles()}.
	 */
	@Test
	void testGenerateArticles() {
		assertThrows(NullPointerException.class, () -> covid.generateArticles());
	}

}