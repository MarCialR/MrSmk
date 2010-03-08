package com.mru.mrnicoquitter.data;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class XMLParser {
	private SAXParser mParser;
	private XMLReader mReader;

	public XMLParser() {

		SAXParserFactory f = SAXParserFactory.newInstance();
		try {
			mParser = f.newSAXParser();
			mReader = mParser.getXMLReader();
		} catch (ParserConfigurationException pcEx) {

		} catch (SAXException saxEx) {

		}
	}

	public XMLParser(ContentHandler handler) {

		SAXParserFactory f = SAXParserFactory.newInstance();
		try {
			mParser = f.newSAXParser();
			mReader = mParser.getXMLReader();
			mReader.setContentHandler(handler);
			// mReader.setErrorHandler(handler);
		} catch (ParserConfigurationException pcEx) {

		} catch (SAXException saxEx) {

		}
	}

	public final void parse(InputStream is, ContentHandler handler) {
		setHandler(handler);
		parse(is);
	}
	
	public final void setHandler(ContentHandler handler) {
		mReader.setContentHandler(handler);
		// mReader.setErrorHandler(handler);
	}

	public final void parse(InputStream is) {
		if (mReader.getContentHandler() == null) {

		}

		try {
			InputSource inputSource = new InputSource(is);
			inputSource.setEncoding("UTF-8");
			mReader.parse(inputSource);
		} catch (IOException ioEx) {

		} catch (SAXException saxEx) {

		}
	}
}
