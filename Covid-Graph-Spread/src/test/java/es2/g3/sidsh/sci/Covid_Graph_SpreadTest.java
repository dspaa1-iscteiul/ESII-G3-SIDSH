package es2.g3.sidsh.sci;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Covid_Graph_SpreadTest {
	
	private static Covid_Graph_Spread c;
	
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		c = new Covid_Graph_Spread();
		c.cloneRepository();
		c.fileInfoForEachTag();
	}

	@Test
	void testCloneRepository() throws InvalidRemoteException, TransportException, IOException, GitAPIException {
		assertNotNull(c.getRepo());
	}
	
	@Test 
	void testInfoForEachTag(){
		assertNotNull(c.getTags());
		assertNotEquals(c.getTags().size(),0);
		
	}

	@Test
	void testGetFileInfo() {
		assertNotNull(c.getFileInfo());
		assertNotEquals(c.getFileInfo().size(),0);
	}
}
