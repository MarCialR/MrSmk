package com.mru.mrnicoquitter.ui;

import static com.mru.mrnicoquitter.Global.*;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import com.mru.mrnicoquitter.DevelopingActivity;
import com.mru.mrnicoquitter.R;

public class MrMenu {

	/** respond to menu item selection */
	public static boolean applyMenuChoice(Activity actv,MenuItem item) {
		Class<?> goTo;
		switch (item.getOrder()) {

		case Menu1:
			try {
				goTo = Class.forName(ACVTY_FLOW_CLASS);
				Intent mainIntent = new Intent(actv,goTo); 
				actv.startActivity(mainIntent); 
				actv.finish();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return true;
		case Menu2:

			try {
				goTo = Class.forName(ACVTY_DEVELOPING_CLASS);
				Intent mainIntent = new Intent(actv,goTo); 
				actv.startActivity(mainIntent); 
				actv.finish();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return true;
		case Menu3:
			AppUtils.showToastShort(actv, "MenuOption3 is selected");
			return true;
		case Menu4:
			AppUtils.showToastShort(actv, "MenuOption4 is selected");
			return true;
		}
		return false;
	}
	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	// menu item constants
	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	public static final int Menu1 = Menu.FIRST + 1;
	public static final int Menu2 = Menu.FIRST + 2;
	public static final int Menu3 = Menu.FIRST + 3;
	public static final int Menu4 = Menu.FIRST + 4;

	/** create the menu items */
	public static void populateMenu(Activity actv,Menu menu) {

	  // enable keyb shortcuts, qwerty mode = true means only show keyb shortcuts (not numeric) and vice versa
	  // these only show up in context menu, not options menu
	  menu.setQwertyMode(false);

	  MenuItem item1 = menu.add(0, Menu.NONE, Menu1, "Flow Control");
	  {
	    item1.setAlphabeticShortcut('a');
	    item1.setIcon(AppUtils.resizeImage(actv, R.drawable.ty0, 32, 32));
	  }

	  MenuItem item2 = menu.add(0,Menu.NONE, Menu2, "Developer Options");
	  {
	    item2.setAlphabeticShortcut('b');
	    item2.setIcon(AppUtils.resizeImage(actv, R.drawable.ty1, 32, 32));
	  }

	  MenuItem item3 = menu.add(0, Menu.NONE,Menu3, "MenuOption3");
	  {
	    item3.setNumericShortcut('1');
	    item3.setIcon(AppUtils.resizeImage(actv, R.drawable.ty2, 32, 32));
	  }

	  MenuItem item4 = menu.add(0,Menu.NONE, Menu4, "MenuOption4");
	  {
	    item4.setNumericShortcut('2');
	    item4.setIcon(AppUtils.resizeImage(actv, R.drawable.ty3, 32, 32));
	  }

	}
}
