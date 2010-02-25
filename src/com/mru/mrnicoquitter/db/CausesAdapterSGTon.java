package com.mru.mrnicoquitter.db;

import android.content.Context;

public class CausesAdapterSGTon {

   private static CausesAdapterSGTon INSTANCE;
   private static CausesAdapter list;
 

   private CausesAdapterSGTon() {}
 
   public static CausesAdapterSGTon getInstance(Context c, String [] s) {
	   if (INSTANCE == null){
		   INSTANCE = new CausesAdapterSGTon();
		   list = new CausesAdapter(c, s);
		   return INSTANCE;
	   } else
		   return INSTANCE;
   }
   public CausesAdapter getList(){
	   return list;
   }

}
