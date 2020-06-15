package ESII_G3_SIDSH.covid_evolution_diff;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.CorruptObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevSort;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;

public class Covid_evolution_diff {
	private List<org.eclipse.jgit.lib.Ref> tags;
	private Git rep;
	private List<RevCommit> commits = new ArrayList();
	private static String OldFile;
	private static String NewFile;

	public Covid_evolution_diff() {
		File dir = new File("ES");
		Path p = Paths.get("ES");
		try {
			if ((dir.isDirectory()) && (Files.list(p).findAny().isPresent()))
				try {
					rep = Git.open(dir);
//					rep.pull().call();
				} catch (IOException e) {
					e.printStackTrace();
				}
			else {
				rep = Git.cloneRepository().setURI("https://github.com/vbasto-iscte/ESII1920.git")
						.setDirectory(new File("ES")).call();
			}
			tags = rep.tagList().call();
		} catch (GitAPIException | IOException e) {
			e.printStackTrace();
		}
		try {
			listCommits();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			readFiles();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void listCommits() throws MissingObjectException, IncorrectObjectTypeException, IOException {
		RevWalk walk = new RevWalk(rep.getRepository());
		walk.sort(RevSort.COMMIT_TIME_DESC);
		walk.markStart(walk.parseCommit(rep.getRepository().resolve("HEAD")));
		RevCommit commit = walk.next();
		while (commit != null) {
			for (int i = 0; i != tags.size(); i++)
				try {
					RevCommit c = walk.parseCommit(tags.get(i).getObjectId());
					if (commit == c)
						commits.add(c);
				} catch (IOException e) {
					e.printStackTrace();
				}
			try {
				commit = walk.next();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		walk.close();
	}

	public void readFiles()
			throws MissingObjectException, IncorrectObjectTypeException, CorruptObjectException, IOException {
		RevTree treeIdNew = commits.get(0).getTree();
		RevTree treeIdOld = commits.get(1).getTree();
		try (RevWalk revWalk = new RevWalk(rep.getRepository())) {
			try (TreeWalk treeWalk = new TreeWalk(rep.getRepository())) {

				treeWalk.addTree(treeIdNew);
				treeWalk.addTree(treeIdOld);
				treeWalk.setRecursive(true);
				treeWalk.setFilter(PathFilter.create("covid19spreading.rdf"));
				if (!treeWalk.next()) {
	                throw new IllegalStateException("Did not find expected file:" + "covid19spreading.rdf");
	            }

				ObjectId objectId = treeWalk.getObjectId(0);
				ObjectLoader loader = rep.getRepository().open(objectId);
				NewFile = new String(loader.getBytes(), StandardCharsets.UTF_8);
				
				objectId = treeWalk.getObjectId(1);
				loader = rep.getRepository().open(objectId);
				OldFile = new String(loader.getBytes(), StandardCharsets.UTF_8);
			}
			revWalk.dispose();
		}
		fileCompare();
	}

	public void fileCompare() {
		String first = "";
		String second = "";
		Scanner input1 = new Scanner(OldFile);
		Scanner input2 = new Scanner(NewFile);

		while ((input1.hasNextLine()) && (input2.hasNextLine())) {
			first = input1.nextLine();
			second = input2.nextLine();

			if (!first.equals(second)) {
				OldFile.replaceFirst(first, first.toUpperCase());
				NewFile.replaceFirst(second, second.toUpperCase());
			}

		}

		GenerateHTML ghtml = new GenerateHTML(OldFile, NewFile);
	}

	public static void main(String[] args) {
		Covid_evolution_diff c = new Covid_evolution_diff();
	}
}