package com.mru.mrnicoquitter.lists;
/*
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.mru.mrnicoquitter.beans.Cigar;
import com.mru.mrnicoquitter.beans.CigarGroup;
*/
public class CGASCigarListActivity {/*extends ListView {

	public CGASCigarListActivity(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	private List<Cigar> cigarEntries = new ArrayList<Cigar>();

	private static final String TAG = "CGASCigarListActivity";
	
	private ArrayList<CigarGroup> mGroupsList;
	private ArrayList<Cigar> mAgendaList;
	
	private ArrayList<GroupItem> mGroupViews;
	
    public CGASCigarListActivity(Context context, AttributeSet attrs) 
    {
        super(context, attrs);
        mGroupViews = new ArrayList<GroupItem>();
    }
    
    @Override
    protected final void onFinishInflate()
    {
    	super.onFinishInflate();
    	
        setEmptyView(null);
    }
    
    protected final void prepareListViews(ArrayList<CigarGroup> groupsList,ArrayList<Cigar> contactsList)
    {
        mGroupViews = new ArrayList<GroupItem>(groupsList.size());
    	mGroupsList = groupsList;
    	mAgendaList = contactsList;
    	
        GroupItem groupItem;
        CigarGroup group;
        
        Iterator<CigarGroup> iT = groupsList.iterator();
        while( iT.hasNext() )
        {
        	group = (CigarGroup)iT.next();
        	groupItem = new GroupItem(getContext());
        	groupItem.populate(group,new ArrayList<Cigar>());
        	mGroupViews.add(groupItem);
        }
    }
    
    private class GroupItem extends LinearLayout {
    // Layout para poder poner varios views dentro

    //VARIABLES MIEMBRO 
    	Context mContext;
// el constructor

        public GroupItem(Context context) 
        {
            super(context);

            mContext = context;
            setOrientation(VERTICAL);
        }

// el cargador de aspecto
        public final void populate(final CigarGroup group,ArrayList<Cigar> contactList)
        {
// cada GroupItem representa un grupo con su subgrupo de contactos. Por eso los parámetros.
            LinearLayout content = new LinearLayout(mContext);
            content.setOrientation(HORIZONTAL);
            content.setGravity(Gravity.CENTER_VERTICAL);
// esto es el contenedor  principal de las views de la fila.

// Poblar de contenido el layout 
// Los elementos que quieras que tengan las acciones, sólo tienes que ponerles un Listener 

// Cuando todo está OK, añadimos el layout a la clase
            addView(content, new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        }}
*/
}
