package com.mru.mrnicoquitter.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mru.mrnicoquitter.beans.Cigar;
import static com.mru.mrnicoquitter.Global.*;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

public class Utils {
	public static int getId(String str, Context ctx) {
		return ctx.getResources().getIdentifier(str, DEF_TYPE_RAW,PKG_BASE);
	}

	public static InputStream getInputStreambyName(String str, Context ctx) {
		Resources rr = ctx.getResources();
		return rr.openRawResource(rr.getIdentifier(str, DEF_TYPE_RAW,PKG_BASE));
	}

	public static InputStream getInputStreamById(int id, Context ctx) {
		Resources rr = ctx.getResources();
		return rr.openRawResource(id);
	}

	public static String getRawResourceContentByName(String _rawResourceName, Context ctx) {

		String styledText = "";
		if ( null != _rawResourceName && !_rawResourceName.equals("") )
			styledText = Utils.getRawResourceContent(_rawResourceName, ctx);

		return styledText;
	}
	
	private static String getRawResourceContent(String filename, Context ctx) {

		StringBuffer sb 	= new StringBuffer();
		BufferedReader bin 	= null;
		String s;

		try {
			bin =new BufferedReader(new InputStreamReader(Utils.getInputStreambyName(filename, ctx)));
			while ((s = bin.readLine()) != null) {
				System.out.println(s);
				sb.append(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				bin.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return sb.toString();
	}

	public static String getPrefsContent(String filename) {

		StringBuffer sb 	= new StringBuffer();
		BufferedReader br 	= null;
		String s;
		
		try {
			br = new BufferedReader(new FileReader(new File(filename)));
			while ((s = br.readLine()) != null) {
				System.out.println(s);
				sb.append(s).append(NEWLINE);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public static List<Cigar> getAssetsCigarContents(String filename,AssetManager assets) {

		Gson gson 				= new Gson();
		Type collectionType 	= new TypeToken<ArrayList<Cigar>>(){}.getType();
		BufferedReader bin		= null; 
		List<Cigar> cigars 		= new ArrayList<Cigar>(); 
		String s;

		try {
			bin = new BufferedReader( new InputStreamReader( assets.open("cigar_files/"+filename)));
			while ((s = bin.readLine()) != null) {
				System.gc();
				cigars.addAll((List<Cigar>)gson.fromJson(s, collectionType));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				bin.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		return cigars;
	}	
	
	public static int getRandom(){
		Random generator = new Random();
		return generator.nextInt(167);
	}
	public static int getRandom(int size) {
		Random generator = new Random();
		return generator.nextInt(size);
	}	
//	public static String getRawResourceContentById(int id, Context ctx) {
	//
//			StringBuffer sb 	= new StringBuffer();
//			BufferedReader bin 	= null;
//			String s;
	//
//			try {
//				bin = new BufferedReader(new InputStreamReader(Utils.getInputStreamById(id, ctx)));
//				while ((s = bin.readLine()) != null) {
//					System.out.println(s);
//					sb.append(s);
//				}
	//
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} finally{
//				try {
//					bin.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			return sb.toString();
//		}	
//	public static String getAssetsTextContents(String filename,AssetManager assets) {
	//
//			StringBuffer sb 		= new StringBuffer();
//			InputStreamReader in 	= null;
//			try {
//				in = new InputStreamReader(	assets.open("cigar_files/"+filename));
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			BufferedReader bin = new BufferedReader(in);
	//
//			String s;
//			try {
//				while ((s = bin.readLine()) != null) {
//					System.out.println(s);
//					sb.append(s).append(NEWLINE);
//				}
	//
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return sb.toString();
//		}


}
