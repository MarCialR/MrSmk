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
import java.util.StringTokenizer;

import static com.mru.mrnicoquitter.Global.*;
import com.google.gson.Gson;
import com.mru.mrnicoquitter.beans.Cita;
import com.mru.mrnicoquitter.beans.Stage;
import com.mru.mrnicoquitter.db.flow.generator.FlowXMLParser;
import com.mru.mrnicoquitter.utils.file.windows.FileUtils;

public class Puente {

	private static final String INPUT_FLOW_OBJECTS_FILE 	= "C:\\Users\\BEEP\\Desktop\\ANDROIDING\\eclipse\\workspace\\MrSmkQuitter\\assets\\HELPER_THINGS\\FlowObjects.xml";
	private static final String OUTPUT_FLOW_OBJECTS_INSERTS = "C:\\Users\\BEEP\\Desktop\\ANDROIDING\\eclipse\\workspace\\MrSmkQuitter\\res\\raw\\flow_inserts";
	private static final String OUTPUT_FILE_GSON 			= "C:\\Users\\BEEP\\Desktop\\ANDROIDING\\eclipse\\workspace\\MrSmkQuitter\\assets\\HELPER_THINGS\\globals.txt";
	private static final String INPUT_FILE_CITAS 			= "C:\\Users\\BEEP\\Desktop\\ANDROIDING\\eclipse\\workspace\\MrSmkQuitter\\assets\\HELPER_THINGS\\citas_plain.txt";
	private static final String OUTPUT_FILE_CITAS 			= "C:\\Users\\BEEP\\Desktop\\ANDROIDING\\eclipse\\workspace\\MrSmkQuitter\\assets\\HELPER_THINGS\\citas_inserts.txt";	
	private static final String OUTPUT_DAY_INSERTS 			= "C:\\Users\\BEEP\\Desktop\\ANDROIDING\\eclipse\\workspace\\MrSmkQuitter\\res\\raw\\day_inserts";	
	private static StringBuilder sb;
	private static Gson gson;
	private static ObjectMapper mapper;
	private static boolean json_not_jackson 				= false;
	private static List<Stage> flowObjectsList;
	private static List<Cita> citasObjectsList;
	private static List<String> asJsonListita;
	public static void main() {
		File f_INPUT_FLOW_OBJECTS_FILE 		= new File(INPUT_FLOW_OBJECTS_FILE);
		File f_OUTPUT_FILE_GSON 			= new File(OUTPUT_FILE_GSON);
		File f_OUTPUT_FLOW_OBJECTS_INSERTS	= new File(OUTPUT_FLOW_OBJECTS_INSERTS);
		File f_OUTPUT_DAY_INSERTS			= new File(OUTPUT_DAY_INSERTS);
		File f_INPUT_FILE_CITAS				= new File(INPUT_FILE_CITAS);
		File f_OUTPUT_FILE_CITAS			= new File(OUTPUT_FILE_CITAS);		
		gson 								= new Gson();
		FlowXMLParser parser 				= new FlowXMLParser("");
		mapper 								= new ObjectMapper(); // can reuse, share globally
		citasObjectsList					= parseCitasInputFile(f_INPUT_FILE_CITAS);
		flowObjectsList 					= parser.parse(f_INPUT_FLOW_OBJECTS_FILE);
		asJsonListita 						= getGson(flowObjectsList);

		// for (Entry<Object, Object> entry : System.getProperties().entrySet())
		// {
		// System.out.println(entry.getKey() + ": "+ entry.getValue());
		// }

		System.out.println("f_INPUT_FLOW_OBJECTS_FILE CONTENTS: \n"+ FileUtils.getContents(f_INPUT_FLOW_OBJECTS_FILE));

		String gORj ;
		String globalArrays;
		try {
//			gORj = (String) (json_not_jackson?getGson(listita):mapper.writeValueAsString(listita));
//			setContents(outputPlain, gORj);
			globalArrays = generateGlobalArrays(parser);
			
//			setContents(gsonPlain, globalArrays);
//			System.out.println("NEW FILE CONTENTS: \n" + getContents(gsonPlain));
			
			FileUtils.setContents(f_OUTPUT_FLOW_OBJECTS_INSERTS, generateFlowInserts(flowObjectsList,asJsonListita));
			System.out.println("f_OUTPUT_FLOW_OBJECTS_INSERTS CONTENTS: \n" + FileUtils.getContents(f_OUTPUT_FLOW_OBJECTS_INSERTS));		
			
			FileUtils.setContents(f_OUTPUT_DAY_INSERTS, generateDayInserts());
			System.out.println("f_OUTPUT_DAY_INSERTS CONTENTS: \n" + FileUtils.getContents(f_OUTPUT_DAY_INSERTS));		

			FileUtils.setContents(f_OUTPUT_FILE_CITAS, generateCitasInserts(citasObjectsList));
			System.out.println("f_OUTPUT_DAY_INSERTS CONTENTS: \n" + FileUtils.getContents(f_OUTPUT_DAY_INSERTS));		
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	private static List<Cita> parseCitasInputFile(File f_INPUT_FILE_CITAS) {
		
		citasObjectsList 	= new ArrayList<Cita>();
		Cita cita 			= null;
		//StringTokenizer st = new StringTokenizer( "hello,this.is:a;test", ",.:;");
		String[] arr		= null;
		// ...checks on aFile are elided
		try {
			// use buffering, reading one line at a time
			// FileReader always assumes default encoding is OK!
			BufferedReader input = new BufferedReader(new FileReader(f_INPUT_FILE_CITAS));
			try {
				String line = null; // not declared within while loop
				/*
				 * readLine is a bit quirky : it returns the content of a line
				 * MINUS the newline. it returns null only for the END of the
				 * stream. it returns an empty String if two newlines appear in
				 * a row.
				 */
				int couter = 0;
				while ((line = input.readLine()) != null) {
					
					try{
					if ( !line.trim().equals("")){
						cita = new Cita();
						arr = line.split("\t");
						cita.setText(arr[1].trim());
						cita.setAuthor(arr[2].trim());
						
						citasObjectsList.add(cita);
					}
					}catch (java.lang.ArrayIndexOutOfBoundsException ex){
						System.out.println(couter);
					}
					couter++;
					
				}
			} finally {
				input.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return citasObjectsList;
	}



	private static String generateCitasInserts(List<Cita> citasObjectsList2) {
		sb = new StringBuilder();
		int counter = 0;
		for (Cita it : citasObjectsList2){
			sb.append("INSERT INTO " + DB_FLOW_TABLE + " (" + FLOW_KEY_ID + "," + FLOW_KEY_OBJECT + ") " +
					 "VALUES (" + it.getAuthor()+ ", '" + it.getText()).append("');\n");
			counter++;
		}
		return sb.toString();
	}



	private static String generateGlobalArrays(FlowXMLParser parser) {
		String OneCodes = "";
		String TwoCodes = "";
		String OneDescriptions = "";
		String TwoDescriptions = "";
		List<String> Descriptions = parser.getDescriptions();
		StringBuffer sb = new StringBuffer();
		int counter = 0;
		for(String code: parser.getCodes()){
			if ( code.startsWith("1")){
				OneCodes += code+",";
				OneDescriptions += "\"" + Descriptions.get(counter)+"\",";
			}
			if ( code.startsWith("2")){
				TwoCodes += code+",";
				TwoDescriptions += "\"" + Descriptions.get(counter)+"\",";
			}
			
			counter++;
		}
		
		sb.append("public static int[] oneCodes = {").append(OneCodes.substring(0, OneCodes.length()-2)).append("};\n");
		sb.append("public static int[] twoCodes = {").append(TwoCodes.substring(0, TwoCodes.length()-2)).append("};\n");
		sb.append("public static String[] oneDescriptions = {").append(OneDescriptions.substring(0, OneDescriptions.length()-1)).append("\"};\n");
		sb.append("public static String[] twoDescriptions = {").append(TwoDescriptions.substring(0, TwoDescriptions.length()-1)).append("\"};\n");			
	

		return sb.toString();
	}

	private static String generateFlowInserts(List<Stage> listita, List<String> listitaJSON) {
		sb = new StringBuilder();
		int counter = 0;
		for (Stage it : listita){
			sb.append("INSERT INTO " + DB_FLOW_TABLE + " (" + FLOW_KEY_ID + "," + FLOW_KEY_OBJECT + ") " +
					 "VALUES (" + it.getObject_id() + ", '" + listitaJSON.get(counter)).append("');\n");
			counter++;
		}
		return sb.toString();
	}

	private static String generateDayInserts() {
		sb = new StringBuilder();
		sb.append("INSERT INTO " + DB_CIGARS_H_TABLE+ " (" + CIGARS_H_KEY_DAY + "," + CIGARS_H_KEY_COUNT+ ") " + "VALUES (1,17);\n");
		sb.append("INSERT INTO " + DB_CIGARS_H_TABLE+ " (" + CIGARS_H_KEY_DAY + "," + CIGARS_H_KEY_COUNT+ ") " + "VALUES (2,23);\n");
		sb.append("INSERT INTO " + DB_CIGARS_H_TABLE+ " (" + CIGARS_H_KEY_DAY + "," + CIGARS_H_KEY_COUNT+ ") " + "VALUES (3,19);\n");
		sb.append("INSERT INTO " + DB_CIGARS_H_TABLE+ " (" + CIGARS_H_KEY_DAY + "," + CIGARS_H_KEY_COUNT+ ") " + "VALUES (4,32);\n");	
		return sb.toString();
	}
	
	private static List<String> getGson(List<Stage> listaInserts) {
		asJsonListita = new ArrayList<String> ();
		try {
			for (Stage it : listaInserts){
				asJsonListita.add(mapper.writeValueAsString(it));
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


}
