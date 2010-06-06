package com.mru.mrnicoquitter.db.flow.generator;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.content.Context;

import com.mru.mrnicoquitter.beans.Stage;
import com.mru.mrnicoquitter.utils.Utils;

public class FlowXMLParser {
	private SAXParser mParser;
	private XMLReader mReader;
	private List<Stage> itemsList;
	private Context ctx;

	public FlowXMLParser(String string, Context _ctx) {

		ctx = _ctx;
		SAXParserFactory f = SAXParserFactory.newInstance();
		try {
			mParser = f.newSAXParser();
			mReader = mParser.getXMLReader();
			itemsList = new ArrayList<Stage>();

		} catch (ParserConfigurationException pcEx) {

		} catch (SAXException saxEx) {

		}
	}

	public final List<Stage> parse() {
		InputStream is =  null;
		if (ctx!=null)
			is = Utils.getInputStreambyName(itemsList.toString()/*getEncuestaName()*/,ctx);
		else{
			// recogerlo por metodo clasico de filesistem
		}
		
		if (mReader.getContentHandler() == null) {
			mReader.setContentHandler(new FlowItemHandler(itemsList));
		}

		try {
			InputSource inputSource = new InputSource(is);
			inputSource.setEncoding("UTF-8");
			mReader.parse(inputSource);
		} catch (IOException ioEx) {

		} catch (SAXException saxEx) {

		}
		return itemsList;
	}
	public static void main(String[] args) {
		FlowXMLParser parser = new FlowXMLParser("", null);
		List<Stage> listita = parser.parse();
		System.out.println(listita);

	}
	
}
