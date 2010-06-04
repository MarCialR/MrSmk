package com.mru.mrnicoquitter;

public  class Global {
	
	public static final String DATABASE_NAME 					= "mrQuitter.db";
	
	public static final String DEF_TYPE_RAW						= "raw";

	public static final String STR_EXTRA_FILENAME				= "fileName";
	public static final String STR_EXTRA_CLICKED_FILE_LOCATION	= "clickedFileLocation";
	public static final String STR_EXTRA_FULL_CONTENTS			= "fullContents";
	public static final String STR_EXTRA_NEWLAINEA				= "ponerNEWLINES_A_las_prEFS";
	
	public static final String DEBUG							= "DEBUG";	
	public static final String EMPTY							= "";
	public static final String TRUE								= "TRUE";
	public static final String NEWLINE							= "\n";	
	

	public static final String PREF_ACTIVE_ACVTY				= "active";
	public static final String PREF_ACTUAL_STAGE				= "ACTUAL STAGE";
	public static final String PREF_CREATED						= "created";
	public static final String PREF_FIRST_RUN					= "FIRST_RUN";
	
	public static final String DIR_SHARED_PREFS					= "/data/data/com.mru.mrnicoquitter/shared_prefs/";	
	
	public static final String GLOBAL_PREFS 					= "GLOBAL";
	
    // =========================================================== 
    // 						STAGES
    // ===========================================================
	public static final int NO_DEF								= -999;	
	public static final int S1									= 1;
	public static final int S2									= 2;
	public static final int S3									= 3;
	public static final int S4									= 4;
	public static final String S1_PREFS 						= "S1_PREFS";
	public static final String S1_STAGE 						= "S1_Stage";
	public static final String S2_PREFS 						= "S2_PREFS";
	public static final String S2_STAGE 						= "S2_Stage";
	
	public static final String PKG_BASE							= "com.mru.mrnicoquitter";
	
    // =========================================================== 
    // 						ACTIVITIES
    // =========================================================== 
/*
	public static final String ACVTY_MAIN						= "MainActivity";
	public static final String ACVTY_DEVELOPING					= "DevelopingActivity";
	public static final String ACVTY_FLOW						= "FlowActivity";
	public static final String ACVTY_ENCUESTA					= "EncuestaListActivity";
	public static final String ACVTY_HTML						= "HTMLViewer";
	public static final String ACVTY_CIGARLIST					= "CigarListActivity";
	public static final String ACVTY_PREFSLIST					= "PrefsListActivity";
*/
	
	public static final String ACVTY_MAIN_CLASS					= "com.mru.mrnicoquitter.MainActivity";
	public static final String ACVTY_DEVELOPING_CLASS			= "com.mru.mrnicoquitter.DevelopingActivity";
	public static final String ACVTY_FLOW_CLASS					= "com.mru.mrnicoquitter.FlowActivity";
	public static final String ACVTY_ENCUESTA_CLASS				= "com.mru.mrnicoquitter.lists.EncuestaListActivity";
	public static final String ACVTY_HTML_CLASS					= "com.mru.mrnicoquitter.viewers.HTMLViewer";
	public static final String ACVTY_CIGARLIST_CLASS			= "com.mru.mrnicoquitter.lists.CigarListActivity";
	public static final String ACVTY_PREFSLIST_CLASS			= "com.mru.mrnicoquitter.lists.PrefsListActivity";
	//public static final String 
    public static final int SPLASH_DISPLAY_LENGHT 				= 100;

    // =========================================================== 
    // 						DATABASES
    // =========================================================== 
    
	public static final String 	DB_FLOW_TABLE 		= "FLOW_TABLE";
	public static final int 	DB_FLOW_VERSION 	= 1;
	public static final String 	FLOW_COL_ID			= "id";	// The index (key) column name for use in where clauses.
	public static final String 	FLOW_KEY_OBJECT		= "object";	// The name and column index of each column in your database.
	public static final int 	FLOW_COL_OBJECT 	= 1;
	public static final String 	DB_FLOW_CREATE 		= "create table " +	DB_FLOW_TABLE + " (" + FLOW_COL_ID + " integer primary key, " + FLOW_KEY_OBJECT + " string not null);";
	public static final String 	DB_FLOW_DROP 		= "DROP TABLE IF EXISTS " + DB_FLOW_TABLE;
	
	
	public static final String 	DB_CIGARS_TABLE 	= "CIGARS_TABLE";
	public static final int 	DB_CIGARS_VERSION 	= 1;
	public static final String 	CIGARS_KEY_ID		= "_id";
	public static final String 	CIGARS_KEY_DATE		= "date";
	public static final int 	CIGARS_COL_DATE 	= 1;
	public static final String 	CIGARS_KEY_TYPE		= "type";
	public static final int 	CIGARS_COL_TYPE 	= 2;
	public static final String 	DB_CIGARS_CREATE	= "create table " +	DB_CIGARS_TABLE + " (" + CIGARS_KEY_ID + " integer primary key autoincrement, " + CIGARS_KEY_DATE + " date not null, "+ CIGARS_KEY_TYPE + " integer not null);";
	public static final String 	DB_CIGARS_DROP 		= "DROP TABLE IF EXISTS " + DB_CIGARS_TABLE;	
    

    
}
