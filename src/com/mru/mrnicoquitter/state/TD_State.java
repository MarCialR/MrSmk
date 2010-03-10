package com.mru.mrnicoquitter.state;

import android.content.Context;

public class TD_State extends State {

	Context myContext;
public TD_State(Context myContext){
	this.myContext= myContext;
	preferences = myContext.getSharedPreferences("MyPrefsFile", 0);
}
	
}
