package com.mru.mrnicoquitter.data;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.content.Context;

import com.mru.mrnicoquitter.beans.Encuesta;
import com.mru.mrnicoquitter.utils.Utils;

public class XMLParser {
	private SAXParser mParser;
	private XMLReader mReader;
	private Encuesta encuesta;
	private Context ctx;

	public XMLParser(String string, Context _ctx) {

		ctx = _ctx;
		SAXParserFactory f = SAXParserFactory.newInstance();
		try {
			mParser = f.newSAXParser();
			mReader = mParser.getXMLReader();
			encuesta = new Encuesta(string);

		} catch (ParserConfigurationException pcEx) {

		} catch (SAXException saxEx) {

		}
	}

	public final Encuesta parse() {
		InputStream is = Utils.getInputStreambyName(encuesta.getEncuestaName(),
				ctx);
		if (mReader.getContentHandler() == null) {
			mReader.setContentHandler(new EncuestaHandler(encuesta));
		}

		try {
			InputSource inputSource = new InputSource(is);
			inputSource.setEncoding("UTF-8");
			mReader.parse(inputSource);
		} catch (IOException ioEx) {

		} catch (SAXException saxEx) {

		}
		return encuesta;
	}
	
/*	Not used any more

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
	*/
	
}
