package es2.g3.sidsh.sci;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class HTMLTableTest {
	private static HTMLTable t;
	private static PrintStream sysOut;
    private final static ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	static void setUpBeforeClass() throws Exception {
		sysOut=System.out;
		System.setOut(new PrintStream(outContent));
	}
	
	@After
    void after() {
        System.setOut(sysOut);
    }

	@Test
	void testMain() throws InvalidRemoteException, TransportException, IOException, GitAPIException, ParseException {
		HTMLTable.main(null);
		assertNotNull(outContent);
		
	}

}
