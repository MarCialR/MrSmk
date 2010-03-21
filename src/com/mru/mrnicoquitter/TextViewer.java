package com.mru.mrnicoquitter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.mru.mrnicoquitter.R;

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
          
  		FileReader fr;
		StringBuffer sb = new StringBuffer();
		try {
			fr = new FileReader(new File(str));

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
textArea.setText(sb.toString());

     } 
}