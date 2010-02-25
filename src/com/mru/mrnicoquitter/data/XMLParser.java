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

	private String mError;

	public XMLParser() {
		mError = "";
		SAXParserFactory f = SAXParserFactory.newInstance();
		try {
			mParser = f.newSAXParser();
			mReader = mParser.getXMLReader();
		} catch (ParserConfigurationException pcEx) {
			mError = pcEx.getMessage();
		} catch (SAXException saxEx) {
			mError = saxEx.getMessage();
		}
	}

	public XMLParser(ContentHandler handler) {
		mError = "";
		SAXParserFactory f = SAXParserFactory.newInstance();
		try {
			mParser = f.newSAXParser();
			mReader = mParser.getXMLReader();
			mReader.setContentHandler(handler);
			// mReader.setErrorHandler(handler);
		} catch (ParserConfigurationException pcEx) {
			mError = pcEx.getMessage();
		} catch (SAXException saxEx) {
			mError = saxEx.getMessage();
		}
	}

	public final void setHandler(ContentHandler handler) {
		mReader.setContentHandler(handler);
		// mReader.setErrorHandler(handler);
	}

	public final String getError() {
		return mError;
	}

	public final void resetError() {
		mError = "";
	}

	public final void parse(InputStream is) {
		if (mReader.getContentHandler() == null) {
			mError = "There is no handler in the XMLReader";
		}

		try {
			InputSource inputSource = new InputSource(is);
			inputSource.setEncoding("UTF-8");
			mReader.parse(inputSource);
		} catch (IOException ioEx) {
			mError = ioEx.getMessage();
		} catch (SAXException saxEx) {
			mError = saxEx.getMessage();
		}
	}

	public final void parse(InputStream is, ContentHandler handler) {
		setHandler(handler);
		parse(is);
	}
}
