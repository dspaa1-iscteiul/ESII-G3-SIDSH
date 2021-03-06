package es2.g3.sidsh.sci;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe que representa um artigo
 * @author dariop
 *
 */
public class Article  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8111379870462365232L;
	private String articles_folder;
	private String fileName;
	private String title;
	private String journal_name;
	private String pub_year;
	private ArrayList<String> authors;
	
	/**
	 * Constrututor de um artigo, com os seguintes parametros
	 * @param articles_folder
	 * @param fileName
	 * @param title
	 * @param journal_name
	 * @param pub_year
	 * @param authors
	 */
	public Article(String articles_folder, String fileName, String title, String journal_name, String pub_year, ArrayList<String> authors) {
		this.articles_folder = articles_folder;
		this.fileName = fileName;
		this.title = title;
		this.journal_name = journal_name;
		this.pub_year = pub_year;
		this.authors = authors;
	}
	
	/**
	 * Construtor default: cria um artigo mas apenas deixa as variaveis em branco
	 */
	public Article() {
		articles_folder="";
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
	
	public String getArticles_folder() {
		return articles_folder;
	}
	
	public void setArticles_folder(String articles_folder) {
		this.articles_folder = articles_folder;
	}
	
	/**
	 * Devolve uma string personalizada dos autores do artigo
	 * @return
	 */
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
	
	/**
	 * Exporta os metadados do artigo para um ficheiro
	 * @throws IOException
	 */
	public void exportToFile() throws IOException {
		File f = new File(articles_folder + fileName + ".metadata");
		FileOutputStream file = new FileOutputStream(f);
		ObjectOutputStream oos = new ObjectOutputStream(file);
		oos.writeObject(this);
		oos.close();
		file.close();
	}
	
	/**
	 * Cria um novo artigo com base em um ficheiro de metadados
	 * @param meta
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static Article newArticle(File meta) throws ClassNotFoundException, IOException {
		FileInputStream file = new FileInputStream(meta);
		ObjectInputStream ois = new ObjectInputStream(file);
		Article article = (Article) ois.readObject();
		ois.close();
		file.close();
		return article;
	}
	
	

}