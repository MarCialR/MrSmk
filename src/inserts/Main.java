package inserts;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import static inserts.Global.*;
import com.google.gson.Gson;

public class Main {

	private static final String INPUT_FILE = "C:\\Users\\BEEP\\Desktop\\ANDROIDING\\eclipse\\workspace\\MrSmkQttr\\assets\\HELPER_THINGS\\test.xml";
	private static final String OUTPUT_FILE_GSON = "C:\\Users\\BEEP\\Desktop\\ANDROIDING\\eclipse\\workspace\\MrSmkQttr\\assets\\HELPER_THINGS\\output.txt";
	private static final String OUTPUT_FILE_INSERTS = "C:\\Users\\BEEP\\Desktop\\ANDROIDING\\eclipse\\workspace\\MrSmkQttr\\assets\\HELPER_THINGS\\flow_inserts";
	private static StringBuilder sb;
	private static Gson gson;
	private static ObjectMapper mapper;
	private static boolean json_not_jackson = false;
	private static List<Stage> listita;
	private static List<String> asJsonListita;
	public static void main(String[] args) {
		File inputFile 			= new File(INPUT_FILE);
		File outputPlain 		= new File(OUTPUT_FILE_GSON);
		File outputINSERTS		= new File(OUTPUT_FILE_INSERTS);
		gson 					= new Gson();
		FlowXMLParser parser 	= new FlowXMLParser("");
		mapper = new ObjectMapper(); // can reuse, share globally
		listita = parser.parse(inputFile);
		asJsonListita = new ArrayList<String> ();
		asJsonListita = getGson(listita);

		// for (Entry<Object, Object> entry : System.getProperties().entrySet())
		// {
		// System.out.println(entry.getKey() + ": "+ entry.getValue());
		// }

		System.out.println("ORIGINAL FILE CONTENTS: \n"+ getContents(inputFile));

		String gORj ;
		try {
			gORj = (String) (json_not_jackson?getGson(listita):mapper.writeValueAsString(listita));
			setContents(outputPlain, gORj);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("NEW FILE CONTENTS: \n" + getContents(outputPlain));
		
		try {
			setContents(outputINSERTS, getInserts(listita,asJsonListita));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("NEW FILE CONTENTS: \n" + getContents(outputINSERTS));		
	}

	private static String getInserts(List<Stage> listita, List<String> listitaJSON) {
		sb = new StringBuilder();
		int counter = 0;
		for (Stage it : listita){
			sb.append("INSERT INTO " + DB_FLOW_TABLE + " (" + FLOW_KEY_ID + "," + FLOW_KEY_OBJECT + ") " +
					 "VALUES (" + it.getId() + ", '" + listitaJSON.get(counter)).append("');\n");
			counter++;
		}
		return sb.toString();
	}

	private static List<String> getGson(List<Stage> listita) {

		try {
			for (Stage it : listita){
				String xxx = mapper.writeValueAsString(it);
				asJsonListita.add(xxx);
			}
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return asJsonListita;
	}

	/**
	 * Fetch the entire contents of a text file, and return it in a String. This
	 * style of implementation does not throw Exceptions to the caller.
	 * 
	 * @param aFile
	 *            is a file which already exists and can be read.
	 */
	static public String getContents(File aFile) {
		// ...checks on aFile are elided
		StringBuilder contents = new StringBuilder();

		try {
			// use buffering, reading one line at a time
			// FileReader always assumes default encoding is OK!
			BufferedReader input = new BufferedReader(new FileReader(aFile));
			try {
				String line = null; // not declared within while loop
				/*
				 * readLine is a bit quirky : it returns the content of a line
				 * MINUS the newline. it returns null only for the END of the
				 * stream. it returns an empty String if two newlines appear in
				 * a row.
				 */
				while ((line = input.readLine()) != null) {
					contents.append(line);
					contents.append(System.getProperty("line.separator"));
				}
			} finally {
				input.close();
			}
		} catch (IOException ex) {
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
	 * @param aFile
	 *            is an existing file which can be written to.
	 * @throws IllegalArgumentException
	 *             if param does not comply.
	 * @throws FileNotFoundException
	 *             if the file does not exist.
	 * @throws IOException
	 *             if problem encountered during write.
	 */
	static public void setContents(File aFile, String aContents)
			throws FileNotFoundException, IOException {
		if (aFile == null) {
			throw new IllegalArgumentException("File should not be null.");
		}
		if (!aFile.exists()) {
			throw new FileNotFoundException("File does not exist: " + aFile);
		}
		if (!aFile.isFile()) {
			throw new IllegalArgumentException("Should not be a directory: "
					+ aFile);
		}
		if (!aFile.canWrite()) {
			throw new IllegalArgumentException("File cannot be written: "
					+ aFile);
		}

		// use buffering
		Writer output = new BufferedWriter(new FileWriter(aFile));
		try {
			// FileWriter always assumes default encoding is OK!
			output.write(aContents);
		} finally {
			output.close();
		}
	}
}