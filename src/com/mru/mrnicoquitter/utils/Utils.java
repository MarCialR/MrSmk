package com.mru.mrnicoquitter.utils;

import java.io.InputStream;

import android.content.Context;
import android.content.res.Resources;

public  class Utils {
	public static int getId(String str, Context ctx){
		return ctx.getResources().getIdentifier(str, "raw", "R");
	}
	public static InputStream getInputStreambyName(String str, Context ctx){
		Resources rr = ctx.getResources();
		return  rr.openRawResource(rr.getIdentifier(str, "raw", "com.mru.mrnicoquitter"));           
	}

}
