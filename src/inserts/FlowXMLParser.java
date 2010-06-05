package inserts;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class FlowXMLParser {
	private SAXParser mParser;
	private XMLReader mReader;
	private List<FlowItem> itemsList;

	public FlowXMLParser(String string) {

		SAXParserFactory f = SAXParserFactory.newInstance();
		try {
			mParser = f.newSAXParser();
			mReader = mParser.getXMLReader();
			itemsList = new ArrayList<FlowItem>();
		} catch (Exception ex) {ex.printStackTrace();}
	}

	public final List<FlowItem> parse(File _aFile) {
		InputStream is = null;
		try {
			is = new BufferedInputStream(new FileInputStream(_aFile));
		} catch (FileNotFoundException e) {e.printStackTrace();}

//		if (ctx!=null)
//			is = Utils.getInputStreambyName(itemsList.toString()/*getEncuestaName()*/,ctx);
//		else{
//			// recogerlo por metodo clasico de filesistem
//		}
		FlowItemHandler flowsList = new FlowItemHandler(itemsList);
		
		if (mReader.getContentHandler() == null) {
			mReader.setContentHandler(flowsList);
		}

		try {
			InputSource inputSource = new InputSource(is);
			inputSource.setEncoding("UTF-8");
			mReader.parse(inputSource);
		} catch (IOException ioEx) {

		} catch (SAXException saxEx) {

		}
		itemsList = flowsList.getList();
		return itemsList;
	}

}
