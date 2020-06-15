package ESII_G3_SIDSH.covid_evolution_diff;

public class GenerateHTML {
	 public GenerateHTML(String antigo, String novo)
	  {
	    System.out.println(cgi_lib.Header());
	    System.out.println(cgi_lib.HtmlTop("Covid Evolution Diff"));
	    System.out.println("<h1>Covid Evolution Diff<h1>");
	    System.out.println("<p style=\"color:#FF0000\";> " + antigo + "</p>");
	    System.out.println("<p style=\"color:#FF0000\";> " + novo + "</p>");
	    System.out.println(cgi_lib.HtmlBot());
	  }
	
}
