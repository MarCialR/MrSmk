package com.mru.mrnicoquitter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.utils.Utils;

import android.app.Activity; 
import android.content.Intent; 
import android.os.Bundle; 
import android.os.Handler; 
import android.widget.TextView;

public class TextViewer extends Activity { 

     
     public void onCreate(Bundle icicle) { 
          super.onCreate(icicle); 
          setContentView(R.layout.text_viewer);
          TextView textArea = (TextView) findViewById(R.id.TextArea);
          Intent i = getIntent();

          String str = i.getStringExtra("clickedFileLocation");
String str2 = i.getStringExtra("file");
if (!str2.equals("")){
    textArea.setText(getFileContent2(str2));
}else
          textArea.setText(getFileContent(str));

     } 
     
     private String getFileContent(String filename){
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
     
     private String getFileContent2(String filename){

 		StringBuffer sb = new StringBuffer();

	  InputStreamReader in= new InputStreamReader(Utils.getInputStreambyName(filename, getApplicationContext()));
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
     
}