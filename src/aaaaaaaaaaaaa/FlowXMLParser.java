package aaaaaaaaaaaaa;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
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

		} catch (ParserConfigurationException pcEx) {

		} catch (SAXException saxEx) {

		}
	}

	public final List<FlowItem> parse() {
		InputStream is = null;
		try {
			is = new BufferedInputStream(new FileInputStream("C:\\Users\\BEEP\\Desktop\\ANDROIDING\\eclipse\\workspace\\MrSmkQttr\\assets\\HELPER_THINGS\\test.xml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  //  InputStream is = new BufferedInputStream(new FileInputStream("image.gif"));
//		if (ctx!=null)
//			is = Utils.getInputStreambyName(itemsList.toString()/*getEncuestaName()*/,ctx);
//		else{
//			// recogerlo por metodo clasico de filesistem
//		}
		FlowItemHandler aaaaaaa = new FlowItemHandler(itemsList);
		
		if (mReader.getContentHandler() == null) {
			mReader.setContentHandler(aaaaaaa);
		}

		try {
			InputSource inputSource = new InputSource(is);
			inputSource.setEncoding("UTF-8");
			mReader.parse(inputSource);
		} catch (IOException ioEx) {

		} catch (SAXException saxEx) {

		}
itemsList = aaaaaaa.getEncuesta();
		return itemsList;
	}
	public static void main(String[] args) {
		
	    File inputFile = new File("C:\\Users\\BEEP\\Desktop\\ANDROIDING\\eclipse\\workspace\\MrSmkQttr\\assets\\HELPER_THINGS\\test.xml");
	    System.out.println("Original file contents: /n" + getContents(inputFile));
	    File outputFile = new File("C:\\Users\\BEEP\\Desktop\\ANDROIDING\\eclipse\\workspace\\MrSmkQttr\\assets\\HELPER_THINGS\\output.txt");
	    try {
			setContents(outputFile, "The content of this file has been overwritten...");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println("New file contents: " + getContents(outputFile));
	    
		FlowXMLParser parser = new FlowXMLParser("");
		List<FlowItem> listita = parser.parse();
		System.out.println(listita);

	}
	  /**
	  * Fetch the entire contents of a text file, and return it in a String.
	  * This style of implementation does not throw Exceptions to the caller.
	  *
	  * @param aFile is a file which already exists and can be read.
	  */
	  static public String getContents(File aFile) {
	    //...checks on aFile are elided
	    StringBuilder contents = new StringBuilder();
	    
	    try {
	      //use buffering, reading one line at a time
	      //FileReader always assumes default encoding is OK!
	      BufferedReader input =  new BufferedReader(new FileReader(aFile));
	      try {
	        String line = null; //not declared within while loop
	        /*
	        * readLine is a bit quirky :
	        * it returns the content of a line MINUS the newline.
	        * it returns null only for the END of the stream.
	        * it returns an empty String if two newlines appear in a row.
	        */
	        while (( line = input.readLine()) != null){
	          contents.append(line);
	          contents.append(System.getProperty("line.separator"));
	        }
	      }
	      finally {
	        input.close();
	      }
	    }
	    catch (IOException ex){
	      ex.printStackTrace();
	    }
	    
	    return contents.toString();
	  }

	  /**
	  * Change the contents of text file in its entirety, overwriting any
	  * existing text.
	  *
	  * This style of implementation throws all exceptions to the caller.
	  *
	  * @param aFile is an existing file which can be written to.
	  * @throws IllegalArgumentException if param does not comply.
	  * @throws FileNotFoundException if the file does not exist.
	  * @throws IOException if problem encountered during write.
	  */
	  static public void setContents(File aFile, String aContents)
	                                 throws FileNotFoundException, IOException {
	    if (aFile == null) {
	      throw new IllegalArgumentException("File should not be null.");
	    }
	    if (!aFile.exists()) {
	      throw new FileNotFoundException ("File does not exist: " + aFile);
	    }
	    if (!aFile.isFile()) {
	      throw new IllegalArgumentException("Should not be a directory: " + aFile);
	    }
	    if (!aFile.canWrite()) {
	      throw new IllegalArgumentException("File cannot be written: " + aFile);
	    }

	    //use buffering
	    Writer output = new BufferedWriter(new FileWriter(aFile));
	    try {
	      //FileWriter always assumes default encoding is OK!
	      output.write( aContents );
	    }
	    finally {
	      output.close();
	    }
	  }	
	
}
