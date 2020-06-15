package query;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Hashtable;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jgit.api.Git;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import cgi.cgi_lib;

public class CovidQuery {
	
	public static File cs;
	
	public static void getFile() {
		try {
			Git.cloneRepository()
			  .setURI("https://github.com/vbasto-iscte/ESII1920.git")
			  .call();
			cs = new File("ESII1920/covid19spreading.rdf");
		} catch (Exception e) {
			try {
//				FileUtils.deleteDirectory(new File("ESII1920"));
				Git repo = Git.open(new File("ESII1920"));
				repo.pull().call();
				cs = new File("ESII1920/covid19spreading.rdf");
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
		}
		}
	}
	
	public static void main(String[] args) {
		getFile();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(cs);
	        doc.getDocumentElement().normalize();         
	        
	        String query = "/RDF/NamedIndividual/@*";
	        XPathFactory xpathFactory = XPathFactory.newInstance();
	        XPath xpath = xpathFactory.newXPath();
	        XPathExpression expr = xpath.compile(query);         
	        NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
	        
			System.out.println(cgi_lib.Header());
			
			
			System.out.println("<form method=\"POST\" action=\"/cgi-bin/cgi-form.sh\">");
			System.out.println("<label for=\"regiao\">Escolhe uma regiao:</label>");
			System.out.println("<select id=\"regiao\" name=\"regiao\">");
			for (int i = 0; i < nl.getLength(); i++) {
		       	 String regiao = StringUtils.substringAfter(nl.item(i).getNodeValue(), "#");
		       	 String apresentar = MessageFormat.format(" <option value=\"{0}\">{0}</option>", regiao);
		       	 System.out.println(apresentar);
		        }
			System.out.println("</select>");
			// LISTAR TODOS OS DADOS
			query = "/RDF/DatatypeProperty/@*";
			expr = xpath.compile(query);         
	        nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			
			System.out.println("<label for=\"dados\">Escolha o tipo de dados:</label>");
			System.out.println("<select id=\"dados\" name=\"dados\">");
			for (int i = 0; i < nl.getLength(); i++) {
		       	 String regiao = StringUtils.substringAfter(nl.item(i).getNodeValue(), "#");
		       	 String apresentar = MessageFormat.format(" <option value=\"{0}\">{0}</option>", regiao);
		       	 System.out.println(apresentar);
		        }
			System.out.println("</select>");
			
			System.out.println("<input type=\"submit\" value=\"Submit\">");
			System.out.println("</form>");
			
			Hashtable form_data = cgi_lib.ReadParse(System.in);
			String regiao = (String) form_data.get("regiao");
			String dado = (String) form_data.get("dados");
			
			
			query = String.format("//*[contains(@about,'%s')]/%s/text()", regiao,dado);
			
			expr = xpath.compile(query);
			
			String compilado = (String) expr.evaluate(doc, XPathConstants.STRING);
			
			
	        System.out.println(String.format("O numero de %s em %s e %s: ", dado, regiao, compilado));
	        System.out.println("<p>");
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		
		
		System.out.println(cgi_lib.HtmlBot());
	}

}
