package sidsh.sci;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Article  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8111379870462365232L;
	private String fileName;
	private String title;
	private String journal_name;
	private String pub_year;
	private ArrayList<String> authors;
	
	public Article(String fileName, String title, String journal_name, String pub_year, ArrayList<String> authors) {
		this.fileName = fileName;
		this.title = title;
		this.journal_name = journal_name;
		this.pub_year = pub_year;
		this.authors = authors;
	}
	
	public Article() {
		fileName="";
		title="";
		journal_name="";
		pub_year="";
		authors=new ArrayList<String>();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getJournal_name() {
		return journal_name;
	}

	public void setJournal_name(String journal_name) {
		this.journal_name = journal_name;
	}

	public String getPub_year() {
		return pub_year;
	}

	public void setPub_year(String pub_year) {
		this.pub_year = pub_year;
	}

	public ArrayList<String> getAuthors() {
		return authors;
	}

	public void setAuthors(ArrayList<String> authors) {
		this.authors = authors;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName (String fileName) {
		this.fileName = fileName;
	}
	
	public String getAuthorsString() {
		String autores = "";
		for (String s: authors) {
			if(!autores.equals(""))
				autores += "; ";
			autores += s;
		}
		return autores;
	}

	@Override
	public String toString() {
		return "Article [title=" + title + ", journal_name=" + journal_name + ", pub_year=" + pub_year + ", authors="
				+ getAuthorsString() + "]";
	}
	
	public void exportToFile() throws IOException {
		File f = new File(Covid_SCI_Discoveries.ARTICLES_FOLDER + fileName + ".metadata");
		FileOutputStream file = new FileOutputStream(f);
		ObjectOutputStream oos = new ObjectOutputStream(file);
		oos.writeObject(this);
		oos.close();
		file.close();
	}
	
	public static Article newArticle(File meta) throws ClassNotFoundException, IOException {
		FileInputStream file = new FileInputStream(meta);
		ObjectInputStream ois = new ObjectInputStream(file);
		Article article = (Article) ois.readObject();
		ois.close();
		file.close();
		return article;
	}
	
	

}
