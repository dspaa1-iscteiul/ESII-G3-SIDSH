package es2.g3.sidsh.sci;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
	
	public void cloneRepository()
			throws InvalidRemoteException, TransportException, IOException, GitAPIException {
		Path path = Paths.get(destination);
		File dir=new File(destination);
		if(!dir.exists())
			dir.mkdir();
//		if (Files.isDirectory(path)) {
			if (!Files.list(path).findAny().isPresent() && dir.exists()) {
				repo = Git.cloneRepository().setBare(true).setURI(url).setDirectory(path.toFile())
						.setBranchesToClone(Arrays.asList("refs/heads/master")).setCloneAllBranches(true)
						.call();
			} else {
				repo = Git.open(path.toFile());
				repo.fetch().call();
			
			}
			
		
	}

	public void fileInfoForEachTag() throws GitAPIException, MissingObjectException, IncorrectObjectTypeException, IOException {
		tags = repo.tagList().call();
		for (org.eclipse.jgit.lib.Ref refToTag : tags) {
			System.out.println("Tag: " + refToTag + " " + refToTag.getName().substring(10) + " "
					+ refToTag.getObjectId().getName());

			RevWalk walk = new RevWalk(repo.getRepository());
			RevObject obj = walk.parseAny(refToTag.getObjectId());
			RevCommit commit;
//    	RevTag tag;
			// lightweight tag(points to a Commit)
			if (obj instanceof RevCommit) {
				commit = walk.parseCommit(refToTag.getObjectId());
				FileInfo info=new FileInfo(new Date(commit.getCommitTime() * 1000L), refToTag.getName().substring(10),
						commit.getFullMessage());
				fileInfo.add(info);
//				System.out.println("commit message: " + commit.getFullMessage() + " commit time: "
//						+ new Date(commit.getCommitTime() * 1000L));
				System.out.println(info);
			}
			// annotated tag(points to a tag object)
			// in this particular case every tag points
			// to a commit, not worth using
			// tag.getTaggerIdent() because we want the
			// file timestamp (commit time)
			// and not the time the tag was made
//    	else if(obj instanceof RevTag) {
//    		tag = walk.parseTag(refToTag.getObjectId());
//    		System.out.println("tag message: "+tag.getFullMessage());
//    	}

		}
	}
	
	public ArrayList<FileInfo> getFileInfo(){
		return fileInfo;
	}

//	public void commitForEachTag() throws IOException, NoHeadException, GitAPIException {
//		LogCommand log = repo.log();
//    	Repository r=repo.getRepository();
//    	Ref peeledRef=r.getRefDatabase().peel(tags.get(1));
//    	if(peeledRef.getPeeledObjectId()!=null)
//    		log.add(peeledRef.getPeeledObjectId());
//    	else
//    		log.add(tags.get(1).getObjectId());
//    	
//    	Iterable<RevCommit> logs=log.call();
//    	for(RevCommit rev: logs) {
//    		System.out.println("Commit: " + rev  + ", name: " + rev.getName() + ", id: " + rev.getId().getName());
//    	}
//	}

}
