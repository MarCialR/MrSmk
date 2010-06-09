package com.mru.mrnicoquitter;

public  class Global {

	public static int[] oneCodes = {1001,1002,1003,1004,1005,1006,1007,1008,1009};
	public static int[] twoCodes = {2001,2003};
	public static String[] oneDescriptions = {"a1_bienvenida","NOT_YET_presentacion","NOT_YET_info_tabaquismo","NOT_YET_t_historia_clinica","NOT_YET_razones_para_dejar_de_fumar","t_fagerstrom","t_glover_nilsson","t_motivacion","MAIN"};
	public static String[] twoDescriptions = {"diez_mandamientos","MAIN"};
	public static final String DEF_TYPE_RAW						= "raw";
	public static final String DEBUG							= "DEBUG";	
	public static final String EMPTY							= "";
	public static final String TRUE								= "TRUE";
	public static final String NEWLINE							= "\n";	
	
    // =========================================================== 
    // 						PREFERENCES
    // ===========================================================	
	public static final String PREFERENCES_DIR					= "/data/data/com.mru.mrnicoquitter/shared_prefs/";
	public static final String PREFS_GLOBAL 					= "PREFS_GLOBAL";
	public static final String PREFS_PHASE_1 					= "PREFS_PHASE_1";
	public static final String PREFS_PHASE_2 					= "PREFS_PHASE_2";	

	public static final String PREF_ACTUAL_STAGE				= "ACTUAL_STAGE";
	public static final String PREF_ACTUAL_PHASE_DEHIDRATED		= "ACTUAL_PHASE_DEHIDRATED";
	public static final String PREF_ACTUAL_PHASE_CODE			= "ACTUAL_PHASE_CODE";
	public static final String PREF_CREATED						= "PREF_CREATED";

    // =========================================================== 
    // 						STAGES
    // ===========================================================
	public static final int NO_DEF								= -999;	
	public static final int PHASE_1_CODE						= 1009;
	public static final int PHASE_2_CODE						= 2003;
	public static final int PHASE_3_CODE						= 300;
	public static final int PHASE_4_CODE						= 400;

	public static final String PHASE_1 							= "PHASE_1";
	public static final String PHASE_2 							= "PHASE_2";
	public static final String PHASE_3 							= "PHASE_3";
	public static final String PHASE_4 							= "PHASE_4";
	
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
    // 						INTENTS
    // ===========================================================	
	public static final String STR_EXTRA_ENCUESTA_NAME			= "STR_EXTRA_ENCUESTA_NAME";
	public static final String STR_EXTRA_R_RAW_FILENAME			= "STR_EXTRA_R_RAW_FILENAME";
	public static final String STR_EXTRA_CLICKED_FILE_LOCATION	= "clickedFileLocation";
	public static final String STR_EXTRA_FULL_CONTENTS_TO_SHOW	= "fullContents";
	public static final String STR_EXTRA_NEWLAINEA				= "ponerNEWLINES_A_las_prEFS";
	
    // =========================================================== 
    // 						DATABASES
    // =========================================================== 

	public static final String 	DATABASE_NAME 		= "mrQuitter.db";
    
	public static final String 	DB_FLOW_TABLE 		= "FLOW_TABLE";
	public static final int 	DB_FLOW_VERSION 	= 1;
	public static final String 	FLOW_COL_AUTO		= "auto_id";	// The index (key) column name for use in where clauses.
	public static final String 	FLOW_KEY_ID			= "id";	// The name and column index of each column in your database.
	public static final int 	FLOW_COL_ID		 	= 1;
	public static final String 	FLOW_KEY_OBJECT		= "object";	// The name and column index of each column in your database.
	public static final int 	FLOW_COL_OBJECT 	= 2;
	public static final String 	DB_FLOW_CREATE 		= "create table " +	DB_FLOW_TABLE + " (" + FLOW_COL_AUTO + " integer primary key, " + FLOW_KEY_ID + " integer, " + FLOW_KEY_OBJECT + " string not null);";
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
