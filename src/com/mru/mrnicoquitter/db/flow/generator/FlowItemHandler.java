package com.mru.mrnicoquitter.db.flow.generator;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.mru.mrnicoquitter.beans.Stage;

public class FlowItemHandler extends DefaultHandler {
	
    private boolean in_id; 
    private boolean in_name;
    private Stage item;
    private List<Stage> itemsList;

	public FlowItemHandler(List<Stage> itemsList2) {
		super();
		this.itemsList = itemsList2;
	}

    public void startDocument() throws SAXException { 
		in_id		= false; 
		in_name	= false;
    } 
/*
	<ITEM>
		<ID>1001</ID>
		<NAME>fagerstrom</NAME>
	</ITEM>   
*/  
    
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		if (localName.equals("ITEM")) { 
    		item			= new Stage();
       }else if (localName.equals("ID")) { 
            this.in_id = true; 
       }else if (localName.equals("NAME")) { 
    	   in_name = true; 
       } 
	}
	
    public void endElement(String namespaceURI, String localName, String qName) 
              throws SAXException { 
		if (localName.equals("ITEM")) { 
			itemsList.add(item);
       }else if (localName.equals("ID")) { 
            in_id = false; 
       }else if (localName.equals("NAME")) { 
            in_name = false; 
         } 
    } 

   public void characters(char ch[], int start, int length) { 
         if(this.in_id){ 
             item.setId(Integer.valueOf(new String(ch, start, length).trim()));
         } else  if(this.in_name){ 
        	 String name = new String(ch, start, length).trim();
        	 if (!name.trim().equals(""))
        		 item.setName(name);
         }
   }
    public List<Stage> getEncuesta() { 
        return this.itemsList; 
   }    
    


		
	
    
}
