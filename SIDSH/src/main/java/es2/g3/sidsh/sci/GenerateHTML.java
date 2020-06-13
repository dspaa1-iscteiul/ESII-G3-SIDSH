package es2.g3.sidsh.sci;

import java.util.ArrayList;

import cgi.cgi_lib;

public class GenerateHTML {

	public static void main(String[] args) {
		Covid_SCI_Discoveries cermine = new Covid_SCI_Discoveries();
		ArrayList<Article> articles = cermine.generateArticles();
		
		System.out.println(cgi_lib.Header());
		System.out.println(cgi_lib.HtmlTop("Covid Scientific Discoveries Repository"));
		System.out.println("<h1>Covid Scientific Discoveries Repository<h1>");
		System.out.println("<table style=\"width:100%\">");
		System.out.println("<tr>");
		System.out.println("<th>Article title</th>");
		System.out.println("<th>Journal name</th>");
		System.out.println("<th>Publication year</th>");
		System.out.println("<th>Authors</th>");
		System.out.println("</tr>");
		
		for(Article article : articles) {
			System.out.println("<tr>");
			System.out.println("<td>" + article.getTitle() + "</td>");
			System.out.println("<td>" + article.getJournal_name() + "</td>");
			System.out.println("<td>" + article.getPub_year() + "</td>");
			System.out.println("<td>" + article.getAuthorsString() + "</td>");
			System.out.println("</tr>");
		}
		System.out.println("</table>\n");
		System.out.println(cgi_lib.HtmlBot());

	}

}
