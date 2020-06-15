package query;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Hashtable;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cgi.cgi_lib;


public class CovidQueryTest {


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetFile() {
		CovidQuery.getFile();
		File file = new File("ESII1920/covid19spreading.rdf");
		assertTrue(file.exists());
	}

	@Test
	public void testMain() {
		String str = "regiao=Alentejo&dados=Infecoes";
		ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
		System.setIn(bais);
		CovidQuery.main(new String[] {});
		
		
	}

}
