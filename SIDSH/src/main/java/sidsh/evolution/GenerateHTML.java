package sidsh.evolution;

import java.util.ArrayList;

import cgi.cgi_lib;

public class GenerateHTML {

	public static void main(String[] args) {
		
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
		
		System.out.println("</table>\n");
		System.out.println(cgi_lib.HtmlBot());

	}

}
