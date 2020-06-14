package es2.g3.sidsh.sci;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

public class HTMLTable {

	public static void main(String[] args) throws InvalidRemoteException, TransportException, IOException, GitAPIException {
		Covid_Graph_Spread app=new Covid_Graph_Spread();
		app.cloneRepository();
		app.fileInfoForEachTag();
		ArrayList<FileInfo> fileInfo=app.getFileInfo();
		
		System.out.println(cgi_lib.Header());
		System.out.println(cgi_lib.HtmlTop("Covid-19 Spreading Data"));
		System.out.println("<h1>Covid-19 Spreading Data<h1>");
		System.out.println("<table style0\"width:100%\">");
		System.out.println("<tr>");
		System.out.println("<th>File timestamp</th>");
		System.out.println("<th>File name</th>");
		System.out.println("<th>File tag</th>");
		System.out.println("<th>Tag Description</th>");
		System.out.println("<th>Spread Visualization Link</th>");
		
		for(FileInfo fi : fileInfo) {
			System.out.println("<tr>");
			System.out.println("<td>" + fi.getTimestamp() + "</td>");
			System.out.println("<td>" + fi.getName() + "</td>");
			System.out.println("<td>" + fi.getTag() + "</td>");
			System.out.println("<td>" + fi.getTag_description() + "</td>");
			System.out.println("<td>" + fi.getLink() + "</td>");
			System.out.println("</tr>");
		}
		
		System.out.println("</table>\n");
		System.out.println(cgi_lib.HtmlBot());
		
 }

}
