package com.mru.mrnicoquitter.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.Html;

public  class Utils {
	public static int getId(String str, Context ctx){
		return ctx.getResources().getIdentifier(str, "raw", "R");
	}
	public static InputStream getInputStreambyName(String str, Context ctx){
		Resources rr = ctx.getResources();
		return  rr.openRawResource(rr.getIdentifier(str, "raw", "com.mru.mrnicoquitter"));           
	}

    public static String getRawResourceContent(String filename, Context ctx){

 		StringBuffer sb = new StringBuffer();

	  InputStreamReader in= new InputStreamReader(Utils.getInputStreambyName(filename, ctx));
	    BufferedReader bin= new BufferedReader(in);


 		String s;
 		try {
			while ((s = bin.readLine()) != null) {
				System.out.println(s);
				sb.append(s);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
    }
    

	public static String getPrefsContent(String filename) {
		FileReader fr;
		StringBuffer sb = new StringBuffer();
		try {
			fr = new FileReader(new File(filename));

			BufferedReader br = new BufferedReader(fr);

			String s;
			while ((s = br.readLine()) != null) {
				System.out.println(s);
				sb.append(s);
			}
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();

	}
	
    
    public static String getXXX(Intent i,Context ctx){
    	
    	
		String str2 = i.getStringExtra("fileName");
		CharSequence styledText="";
		if (null != str2 && !str2.equals(""))

			styledText = Html.fromHtml(Utils
					.getRawResourceContent(str2, ctx));

		
    	return styledText.toString();
    }
		
}
