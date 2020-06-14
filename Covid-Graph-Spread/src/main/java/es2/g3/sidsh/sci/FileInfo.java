package es2.g3.sidsh.sci;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class FileInfo {

	private Date timestamp;
	private String name ="covid19spreading.rdf";
	private String tag;
	private String tag_description;
	private URL link;
	
	public FileInfo(Date timestamp, String tag, String tag_description) {
		this.timestamp=timestamp;
		this.tag=tag;
		this.tag_description=tag_description;
		try {
			link=new URL("http://visualdataweb.de/webvowl/#iri=https://github.com/vbasto-iscte/ESII1920/raw/"+tag+"/covid19spreading.rdf");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "FileInfo [timestamp=" + timestamp + ", name=" + name + ", tag=" + tag + ", tag_description="
				+ tag_description + ", link=" + link + "]";
	}

	public Date getTimestamp() {
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
	
	public URL getLink() {
		return link;
	}
}
