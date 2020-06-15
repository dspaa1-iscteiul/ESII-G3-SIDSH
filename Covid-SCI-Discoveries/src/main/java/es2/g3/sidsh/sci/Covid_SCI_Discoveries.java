/**
 * 
 */
package es2.g3.sidsh.sci;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.xpath.XPath;

import pl.edu.icm.cermine.ContentExtractor;
import pl.edu.icm.cermine.exception.AnalysisException;

/**
 * Classe que interpreta os pdfs e extrai a informação
 * 
 * @author dariop
 * 
 */
public class Covid_SCI_Discoveries {

//	public static String ARTICLES_FOLDER = "articles/";
	private String articles_folder;
	private ArrayList<Article> articles = new ArrayList<Article>();

	/**
	 * Cria uma instancia da classe, dada a folder dos artigos
	 * 
	 * @param articles_folder
	 */
	public Covid_SCI_Discoveries(String articles_folder) {
		this.articles_folder = articles_folder;
	}

	/**
	 * Verifica que ficheiros pdf estão na pasta e guarda as suas referencias em um
	 * HashMap (nome do ficheiro, inputStream do ficheiro)
	 * 
	 * @return
	 */
	public HashMap<String, InputStream> getPdfFiles() {
		File folder = new File(articles_folder);
		File[] listOfFiles = folder.listFiles();
		HashMap<String, InputStream> pdfFiles = new HashMap<String, InputStream>();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && isPdf(listOfFiles[i]) && notYetExtracted(listOfFiles[i], true))
				try {
					pdfFiles.put(listOfFiles[i].getName(), new FileInputStream(listOfFiles[i]));
				} catch (FileNotFoundException e) {
					System.out.println("Ficheiro não encontrado!");
					e.printStackTrace();
				}
		}
		return pdfFiles;
	}

	/**
	 * Verifica se um ficheiro ainda não tem metadados, ou seja, se ainda não foi
	 * feita a extração antes
	 * 
	 * @param file
	 * @param createFile - true se quiser criar um ficheiro metadados da extração.
	 *                   Usado como variavel de controlo para os testes JUnit
	 * @return
	 */
	public boolean notYetExtracted(File file, boolean createFile) {
		File meta = new File(articles_folder + file.getName() + ".metadata");
		if (!meta.exists())
			return true;
		else if (createFile) {
			try {
				articles.add(Article.newArticle(meta));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		} else {
			return false;
		}
	}

	/**
	 * Verifica se um dado ficheiro é um ficheiro PDF
	 * @param file
	 * @return
	 */
	public boolean isPdf(File file) {
		if (file.getName().endsWith(".pdf"))
			return true;
		return false;
	}

	/**
	 * Método que extrai a informação pedida do documento NLM da extração
	 * @param nlm
	 * @param xpath
	 * @return
	 * @throws JDOMException
	 */
	public static String extractXPathValue(Document nlm, String xpath) throws JDOMException {
		XPath xPath = XPath.newInstance(xpath);
		String res = xPath.valueOf(nlm);
		if (res != null) {
			res = res.trim();
		}
		return res;
	}

	/**
	 * Extrai os autores do artigo
	 * @param nlm
	 * @param xpath
	 * @return
	 * @throws JDOMException
	 */
	public static ArrayList<String> extractAuthors(Document nlm, String xpath) throws JDOMException {
		XPath xPath = XPath.newInstance(xpath);
		@SuppressWarnings("unchecked")
		List<Element> nodes = xPath.selectNodes(nlm);
		ArrayList<String> authors = new ArrayList<String>();
		for (Element node : nodes) {
			Element nameEl = node.getChild("name");
			String name;
			if (nameEl != null) {
				name = nameEl.getChildText("surname") + ", " + nameEl.getChildText("given-names");
			} else {
				name = node.getChildText("string-name");
			}
			authors.add(name);
		}
		return authors;
	}

	/**
	 * Extrai as informações do artigo pdf e cria uma instancia do artigo
	 * @param nlm
	 * @return
	 */
	public Article extractNLM(Document nlm) {
		try {
			Article article = new Article();
			article.setTitle(extractXPathValue(nlm, "/article/front//article-title"));
			article.setJournal_name(extractXPathValue(nlm, "/article/front//journal-title"));
			article.setAuthors(extractAuthors(nlm, "//contrib[@contrib-type='author']"));
			article.setPub_year(extractXPathValue(nlm, "//pub-date" + "/year"));
			return article;
		} catch (JDOMException ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Gera a lista de artigos que já foram extraidos
	 * @return
	 */
	public ArrayList<Article> generateArticles() {
		HashMap<String, InputStream> pdfs = getPdfFiles();

		for (Map.Entry<String, InputStream> entry : pdfs.entrySet()) {
			try {
				ContentExtractor extractor = new ContentExtractor();
				extractor.setPDF(entry.getValue());
				Element result = extractor.getContentAsNLM();
				Document doc = new Document(result);
				Article article = extractNLM(doc);
				article.setFileName(entry.getKey());
				article.setArticles_folder(articles_folder);
				articles.add(article);
				article.exportToFile();
			} catch (AnalysisException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return articles;
	}

	public String getArticles_folder() {
		return articles_folder;
	}

	public void setArticles_folder(String articles_folder) {
		this.articles_folder = articles_folder;
	}
}