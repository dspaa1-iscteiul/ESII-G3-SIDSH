/**
 * 
 */
package es2.g3.sidsh.sci;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author dariop
 *
 */
class ArticleTest {
	
	static Article article;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		article = new Article();
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		article = new Article();
	}

	/**
	 * Test method for {@link es2.g3.sidsh.sci.Article#Article(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.ArrayList)}.
	 */
	@Test
	void testArticleStringStringStringStringArrayListOfString() {
		@SuppressWarnings("unused")
		Article art = new Article("a", "a.pdf", "a", "a", "2019", new ArrayList<String>());
	}

	/**
	 * Test method for {@link es2.g3.sidsh.sci.Article#getTitle()}.
	 */
	@Test
	void testGetTitle() {
		assertEquals("", article.getTitle());
	}

	/**
	 * Test method for {@link es2.g3.sidsh.sci.Article#setTitle(java.lang.String)}.
	 */
	@Test
	void testSetTitle() {
		article.setTitle("a");
		assertEquals("a", article.getTitle());
	}

	/**
	 * Test method for {@link es2.g3.sidsh.sci.Article#getJournal_name()}.
	 */
	@Test
	void testGetJournal_name() {
		assertEquals("", article.getJournal_name());
	}

	/**
	 * Test method for {@link es2.g3.sidsh.sci.Article#setJournal_name(java.lang.String)}.
	 */
	@Test
	void testSetJournal_name() {
		article.setJournal_name("a");
		assertEquals("a", article.getJournal_name());
	}

	/**
	 * Test method for {@link es2.g3.sidsh.sci.Article#getPub_year()}.
	 */
	@Test
	void testGetPub_year() {
		assertEquals("", article.getPub_year());
	}

	/**
	 * Test method for {@link es2.g3.sidsh.sci.Article#setPub_year(java.lang.String)}.
	 */
	@Test
	void testSetPub_year() {
		article.setPub_year("2019");
		assertEquals("2019", article.getPub_year());
	}

	/**
	 * Test method for {@link es2.g3.sidsh.sci.Article#getAuthors()}.
	 */
	@Test
	void testGetAuthors() {
		article.getAuthors();
	}

	/**
	 * Test method for {@link es2.g3.sidsh.sci.Article#setAuthors(java.util.ArrayList)}.
	 */
	@Test
	void testSetAuthors() {
		ArrayList<String> autor = new ArrayList<String>();
		article.setAuthors(autor);
		assertEquals(autor, article.getAuthors());
	}

	/**
	 * Test method for {@link es2.g3.sidsh.sci.Article#getFileName()}.
	 */
	@Test
	void testGetFileName() {
		assertEquals("", article.getFileName());
	}

	/**
	 * Test method for {@link es2.g3.sidsh.sci.Article#setFileName(java.lang.String)}.
	 */
	@Test
	void testSetFileName() {
		article.setFileName("a.pdf");
		assertEquals("a.pdf", article.getFileName());
	}

	/**
	 * Test method for {@link es2.g3.sidsh.sci.Article#getAuthorsString()}.
	 */
	@Test
	void testGetAuthorsString() {
		assertEquals("", article.getAuthorsString());
	}

	/**
	 * Test method for {@link es2.g3.sidsh.sci.Article#toString()}.
	 */
	@Test
	void testToString() {
		article = new Article();
		String s = "Article [title=, journal_name=, pub_year=, authors=]";
		assertTrue(s.equals(article.toString()));
	}

}