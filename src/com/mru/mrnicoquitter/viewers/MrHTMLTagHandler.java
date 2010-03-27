package com.mru.mrnicoquitter.viewers;

import org.xml.sax.XMLReader;

import android.text.Editable;
import android.text.Html;

public class MrHTMLTagHandler implements Html.TagHandler{

	@Override
	public void handleTag(boolean arg0, String arg1, Editable arg2,
			XMLReader arg3) {
		System.out.println("");
		
	}

}
