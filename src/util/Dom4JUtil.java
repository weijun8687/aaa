package util;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4JUtil {
	private static String dbFilePath;
	static{
		ClassLoader cl = Dom4JUtil.class.getClassLoader();
		URL url = cl.getResource("users.xml");
		dbFilePath = url.getPath();
	}
	public static Document getDocument() throws Exception{
		SAXReader reader = new SAXReader();
		return reader.read(dbFilePath);
	}
	public static void write2xml(Document document) throws Exception{
		OutputStream out = new FileOutputStream(dbFilePath);
		XMLWriter writer = new XMLWriter(out);
		writer.write(document);
		writer.close();
	}
}
