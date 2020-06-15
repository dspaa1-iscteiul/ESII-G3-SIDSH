package es2.g3.sidsh.sci;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FileInfoTest {
	
	private static FileInfo fileInfo;
	
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		fileInfo = new FileInfo("2020-05-26 12:58:22","NovoSurto","Detetado novo surto");
	}

	@Test
	void testFileInfo() {
		assertNotNull(fileInfo);
	}

	@Test
	void testToString() {
		assertEquals(fileInfo.toString(),"FileInfo [timestamp=" + fileInfo.getTimestamp()
		+ ", name=" + fileInfo.getName() + ", tag=" + fileInfo.getTag() + ", tag_description="
				+ fileInfo.getTag_description() + ", link=" + fileInfo.getLink() + "]");
	}

	@Test
	void testGetTimestamp() {
		assertEquals(fileInfo.getTimestamp(),"2020-05-26 12:58:22");
	}

	@Test
	void testGetName() {
		assertEquals(fileInfo.getName(),"covid19spreading.rdf");
	}

	@Test
	void testGetTag() {
		assertEquals(fileInfo.getTag(),"NovoSurto");
	}

	@Test
	void testGetTag_description() {
		assertEquals(fileInfo.getTag_description(),"Detetado novo surto");
	}

	@Test
	void testGetLink() {
		assertEquals(fileInfo.getLink(),"http://visualdataweb.de/webvowl/#iri=https://github.com/vbasto-iscte/ESII1920/raw/NovoSurto/covid19spreading.rdf");
	}

}
