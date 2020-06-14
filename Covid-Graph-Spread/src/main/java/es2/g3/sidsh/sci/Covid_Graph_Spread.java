package es2.g3.sidsh.sci;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevObject;
import org.eclipse.jgit.revwalk.RevTag;
import org.eclipse.jgit.revwalk.RevWalk;


public class Covid_Graph_Spread {
	private Git repo;
	private List<org.eclipse.jgit.lib.Ref> tags;
	private ArrayList<FileInfo> fileInfo = new ArrayList<FileInfo>();
	private static String url = "https://github.com/vbasto-iscte/ESII1920.git";
	private static String destination = "./Repository/";
	
	public Covid_Graph_Spread() throws InvalidRemoteException, TransportException, IOException, GitAPIException, ParseException {
		cloneRepository();
		fileInfoForEachTag();
	}
	
	public void cloneRepository()
			throws InvalidRemoteException, TransportException, IOException, GitAPIException {
		Path path = Paths.get(destination);
		File dir=new File(destination);
		if(!dir.exists())
			dir.mkdir();
			if (!Files.list(path).findAny().isPresent() && dir.exists()) {
				repo = Git.cloneRepository().setBare(true).setURI(url).setDirectory(path.toFile())
						.setBranchesToClone(Arrays.asList("refs/heads/master")).setCloneAllBranches(true)
						.call();
			} else {
				repo = Git.open(path.toFile());
				repo.fetch().call();
			
			}
	}

	public void fileInfoForEachTag() throws GitAPIException, MissingObjectException, IncorrectObjectTypeException, IOException, ParseException {
		tags = repo.tagList().call();
		for (org.eclipse.jgit.lib.Ref refToTag : tags) {
			System.out.println("Tag: " + refToTag + " " + refToTag.getName().substring(10) + " "
					+ refToTag.getObjectId().getName());

			RevWalk walk = new RevWalk(repo.getRepository());
			RevObject obj = walk.parseAny(refToTag.getObjectId());
			RevCommit commit;
			// lightweight tag(points to a Commit)
			if (obj instanceof RevCommit) {
				commit = walk.parseCommit(refToTag.getObjectId());
				DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				df.setTimeZone(TimeZone.getTimeZone("GMT+1"));
				String date=df.format(new Date(commit.getCommitTime() * 1000L));
			
				FileInfo info=new FileInfo(date, refToTag.getName().substring(10),
						commit.getFullMessage());
				fileInfo.add(info);
			}
		}
	}
	
	public ArrayList<FileInfo> getFileInfo(){
		return fileInfo;
	}

}
