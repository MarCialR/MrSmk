package com.mru.mrnicoquitter;

import com.mru.mrnicoquitter.flow.FlowManagerSGTon;
import com.mru.mrnicoquitter.stage.Phase;
import com.mru.mrnicoquitter.ui.AppUtils;
import com.mru.mrnicoquitter.ui.MrMenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public abstract class QActivity extends Activity {
	protected static Phase phase;
	
	protected abstract boolean isOKToLaunch();
	protected abstract String[] getMandatoryFields();	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//OJITO CON ESTO!!! hay que asegurarse que ese Stage es singleton tb (o algo asi)
		if (phase==null)
			phase = FlowManagerSGTon.getPhase();
		AppUtils.showDebug(getApplicationContext(), getActivityName() +" - onCreate!!");
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		AppUtils.showDebug(getApplicationContext(), getActivityName() +" - onDestroy!!");
//		Intent svc = new Intent(this, NotificationService.class);
//	    stopService(svc);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		AppUtils.showDebug(getApplicationContext(), getActivityName() +" - onPause!!");
//		Intent svc = new Intent(this, NotificationService.class);
//	    stopService(svc);
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		AppUtils.showDebug(getApplicationContext(), getActivityName() +" - onRestart!!");
//		Intent svc = new Intent(this, NotificationService.class);
//	    stopService(svc);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		AppUtils.showDebug(getApplicationContext(), getActivityName() +"- onStop!!");

	}
	
	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	// Menu button - option menu
	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

	/** hook into menu button for activity */
	@Override public boolean onCreateOptionsMenu(Menu menu) {
	  MrMenu.populateMenu(this,menu);
	  return super.onCreateOptionsMenu(menu);
	}

	/** when menu button option selected */
	@Override public boolean onOptionsItemSelected(MenuItem item) {
	  return MrMenu.applyMenuChoice(this,item) || super.onOptionsItemSelected(item);
	}	
	

	public String getActivityName() {
		String className = this.getClass().toString();
		return className.substring(0, className.lastIndexOf("."));
	}	

	
	public void AA(Context context, Class<TimelineActivity> classs, int i) {
		startActivityForResult(new Intent(context,classs), i);
		
	} 	
}
