package com.mru.mrnicoquitter.db.flow.generator;


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

import com.mru.mrnicoquitter.beans.Stage;

public class FlowXMLParser {
	private SAXParser mParser;
	private XMLReader mReader;
	private List<Stage> itemsList;
    private List<String> codes;
    private List<String> descriptions;
    
	public FlowXMLParser(String string) {

		SAXParserFactory f = SAXParserFactory.newInstance();
		try {
			mParser = f.newSAXParser();
			mReader = mParser.getXMLReader();
			itemsList = new ArrayList<Stage>();
		} catch (Exception ex) {ex.printStackTrace();}
	}

	public final List<Stage> parse(File _aFile) {
		InputStream is = null;
		try {
			is = new BufferedInputStream(new FileInputStream(_aFile));
		} catch (FileNotFoundException e) {e.printStackTrace();}

//		if (ctx!=null)
//			is = Utils.getInputStreambyName(itemsList.toString()/*getEncuestaName()*/,ctx);
//		else{
//			// recogerlo por metodo clasico de filesistem
//		}
		FlowItemHandler realParser = new FlowItemHandler(itemsList);
		
		if (mReader.getContentHandler() == null) {
			mReader.setContentHandler(realParser);
		}

		try {
			InputSource inputSource = new InputSource(is);
			inputSource.setEncoding("UTF-8");
			mReader.parse(inputSource);
		} catch (IOException ioEx) {

		} catch (SAXException saxEx) {

		}
		itemsList = realParser.getStagesList();
		codes = realParser.getCodes();
		descriptions = realParser.getDescriptions();
		return itemsList;
	}
	public List<String> getCodes() {
		return codes;
	}

	public List<String> getDescriptions() {
		return descriptions;
	}    
    
}
