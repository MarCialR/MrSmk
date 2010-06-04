package aaaaaaaaaaaaa;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class FlowItemHandler extends DefaultHandler {
	
    private boolean in_id; 
    private boolean in_name;
    private FlowItem item;
    private List<FlowItem> itemsList;

	public FlowItemHandler(List<FlowItem> itemsList2) {
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
		if (qName.equals("ITEM")) { 
    		item			= new FlowItem();
       }else if (qName.equals("ID")) { 
            this.in_id = true; 
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
    public List<FlowItem> getEncuesta() { 
        return this.itemsList; 
   }    
    


		
	
    
}
