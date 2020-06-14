package es2.g3.sidsh.sci;

import java.util.ArrayList;

import cgi.cgi_lib;

public class GenerateHTML {

	public static void main(String[] args) {

		Covid_SCI_Discoveries cermine;
		if (args.length != 0) {
			cermine = new Covid_SCI_Discoveries(args[0]);
		} else {
			cermine = new Covid_SCI_Discoveries("./");
		}

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

		if (args.length == 0) {
			System.out.println("<h2>Não foi especificado o caminho para os ficheiros</h2>");
		} else {
			for (Article article : articles) {
				System.out.println("<tr>");
				System.out.println("<td><a href=\"" + cermine.getArticles_folder() + article.getFileName() + "\">"
						+ article.getTitle() + "</a></td>");
				System.out.println("<td>" + article.getJournal_name() + "</td>");
				System.out.println("<td>" + article.getPub_year() + "</td>");
				System.out.println("<td>" + article.getAuthorsString() + "</td>");
				System.out.println("</tr>");
			}
		}
		System.out.println("</table>\n");
		System.out.println(cgi_lib.HtmlBot());

	}

}
