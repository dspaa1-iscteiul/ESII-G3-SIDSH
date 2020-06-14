package es2.g3.sidsh.sci;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class FileInfo {

	private String timestamp;
	private String name ="covid19spreading.rdf";
	private String tag;
	private String tag_description;
	private String link;
	
	public FileInfo(String timestamp, String tag, String tag_description) {
		this.timestamp=timestamp;
		this.tag=tag;
		this.tag_description=tag_description;
		link="http://visualdataweb.de/webvowl/#iri=https://github.com/vbasto-iscte/ESII1920/raw/"+tag+"/covid19spreading.rdf";
	}
	
	@Override
	public String toString() {
		return "FileInfo [timestamp=" + timestamp + ", name=" + name + ", tag=" + tag + ", tag_description="
				+ tag_description + ", link=" + link + "]";
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getName() {
		return name;
	}

	public String getTag() {
		return tag;
	}

	public String getTag_description() {
		return tag_description;
	}
	
	public String getLink() {
		return link;
	}
}
