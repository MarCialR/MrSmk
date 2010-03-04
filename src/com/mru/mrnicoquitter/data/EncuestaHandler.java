package com.mru.mrnicoquitter.data;

import java.util.ArrayList;
import java.util.Iterator;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class EncuestaHandler extends DefaultHandler {
	
    // =========================================================== 
    // Fields 
    // =========================================================== 
     
//    private boolean in_item; 
    private boolean in_ques; 
    private boolean in_option;
    private Encuesta encuesta;
    private Item item;
    
    @Override 
    public void startDocument() throws SAXException { 
		//in_item		= false; 
		in_ques		= false; 
		in_option	= false;
		encuesta 	= new Encuesta();
    } 

    @Override 
    public void endDocument() throws SAXException { 
         // Do some finishing work if needed 
    }    
/*
	<ITEM>
		<QUES code ="QA4562" type="multichoice">Empire Burlesque</QUES>
		<OPTION >At vero eos et accusamus et iusto odio</OPTION>
		<OPTION >dignissimos ducimus qui blanditiis praesentium voluptatum</OPTION>
		<OPTION ">deleniti atque corrupti quos dolores et quas molestias</OPTION>
		<OPTION >excepturi sint occaecati cupiditate non provident,</OPTION>
	</ITEM>   
*/  
    
    
	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		if (localName.equals("ITEM")) { 
//            this.in_item 	= true; 
    		item			= new Item();
       }else if (localName.equals("QUES")) { 
            this.in_ques = true; 
            item.setCode(Integer.valueOf(atts.getValue("code")));
            item.setType(atts.getValue("type"));
       }else if (localName.equals("OPTION")) { 
    	   in_option = true; 
       } 
	}
	
    /** Gets be called on closing tags like: 
     * </tag> */ 
    @Override 
    public void endElement(String namespaceURI, String localName, String qName) 
              throws SAXException { 
		if (localName.equals("ITEM")) { 
//            this.in_item = false; 
            encuesta.add(item);
       }else if (localName.equals("QUES")) { 
            this.in_ques = false; 
       }else if (localName.equals("OPTION")) { 
              // Nothing to do here 
         } 
    } 
    
    /** Gets be called on the following structure: 
     * <tag>characters</tag> */ 
    @Override 
   public void characters(char ch[], int start, int length) { 
         if(this.in_ques){ 
             item.setQues(new String(ch, start, length).trim());
         } else  if(this.in_option){ 
        	 String temita = new String(ch, start, length).trim();
        	 if (!temita.trim().equals(""))
        		 item.addAnswer(temita);
         }
         
   }
    public Encuesta getEncuesta() { 
        return this.encuesta; 
   }    
    
    public class Encuesta{
    	private ArrayList<Item> WIIIIII = new ArrayList<Item>();
    	public void add ( Item it){
    		WIIIIII.add(it);
    	}
    	public Iterator<Item> iterator(){
    		return WIIIIII.iterator();
    	}
    	public ArrayList<Item> getItems(){
    		return WIIIIII;
    	}
    }

	public class Item{
		
		private final int MULTICHOICE 	= 1;
		private final int TEXT 			= 2; 
		private Integer code;
		private Integer type;
		private String ques;
		private ArrayList<String> answs= new ArrayList<String>();
		
		public Integer getCode() {
			return code;
		}
		public void setCode(Integer code) {
			this.code = code;
		}
		public int getType() {
			return type;
		}
		public void setType(String type) {
			
			if (type.equals("multichoice")){
				this.type = MULTICHOICE;
			} else if (type.equals("text")){
				this.type = TEXT;
			} else{
				this.type = MULTICHOICE;
			}
		}
		public String getQues() {
			return ques;
		}
		public void setQues(String ques) {
			this.ques = ques;
		}
		public ArrayList<String> getAnsws() {
			return answs;
		}
		public void addAnswer(String answer) {
			this.answs.add(answer);
		}
		
	}
		
	
    
}
