package inserts;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class FlowItemHandler extends DefaultHandler {
	
    private boolean in_id; 
    private boolean in_name;
    private boolean in_activity;
    private boolean in_extra;

    private List<Stage> itemsList;
    private Stage item;
    private IntentExtra extra;


	public FlowItemHandler(List<Stage> itemsList2) {
		super();
		this.itemsList = itemsList2;
	}

    public void startDocument() throws SAXException { 
		in_id		= false; 
		in_name		= false;
		in_activity = false;
		in_extra	= false;
    } 
/*
	<ITEM>
		<ID>1002</ID>
		<NAME>t_fagerstrom</NAME>
		<ACTIVITY>com.mru.mrnicoquitter.encuestas.EncuestaActivity</ACTIVITY>
		<EXTRA name="STR_EXTRA_ENCUESTA_NAME" >t_fagerstrom</EXTRA>
	</ITEM>    
*/  
    
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		if (qName.equals("ITEM")) { 
    		item			= new Stage();
       }else if (qName.equals("ID")) { 
            this.in_id = true; 
       }else if (qName.equals("ACTIVITY")) { 
    	   in_activity = true; 
       }else if (qName.equals("EXTRA")) {
    	   extra 	= new IntentExtra();
    	   extra.setName(atts.getValue("name"));
    	   in_extra = true; 
       }else if (qName.equals("NAME")) { 
    	   in_name = true; 
       } 
	}
	
    public void endElement(String namespaceURI, String localName, String qName) 
              throws SAXException { 
		if (qName.equals("ITEM")) { 
			itemsList.add(item);
       }else if (qName.equals("ID")) { 
            in_id = false; 
       }else if (qName.equals("NAME")) { 
            in_name = false; 
       }else if (qName.equals("ACTIVITY")) { 
    	   in_activity = false; 
       }else if (qName.equals("EXTRA")) { 
    	   in_extra = false;
    	   item.addExtra(extra);
       }
    } 

   public void characters(char ch[], int start, int length) { 
	   String contents;
         if(this.in_id){ 
             item.setId(Integer.valueOf(new String(ch, start, length).trim()));
         } else  if(this.in_name){ 
        	 contents = new String(ch, start, length).trim();
        	 if (!contents.trim().equals(""))
        		 item.setName(contents);
         } else  if(this.in_extra){ 
        	 contents = new String(ch, start, length).trim();
        	 if (!contents.trim().equals(""))
        		 extra.setContents(contents);
         } else  if(this.in_activity){ 
        	 contents = new String(ch, start, length).trim();
        	 if (!contents.trim().equals(""))
        		 item.setActivity(contents);
         }
   }
    public List<Stage> getList() { 
        return this.itemsList; 
   }    
    


		
	
    
}