package com.mru.mrnicoquitter.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.mru.mrnicoquitter.beans.AnswerItem;
import com.mru.mrnicoquitter.beans.Encuesta;
import com.mru.mrnicoquitter.beans.EncuestaItem;


public class EncuestaHandler extends DefaultHandler {
	
    // =========================================================== 
    // Fields 
    // =========================================================== 
     
//    private boolean in_item; 
    private boolean in_ques; 
    private boolean in_option;
    private Encuesta encuesta;
    private EncuestaItem item;
    private AnswerItem answer;



	public EncuestaHandler(Encuesta encuesta) {
		super();
		this.encuesta = encuesta;
	}


	@Override 
    public void startDocument() throws SAXException { 
		//in_item		= false; 
		in_ques		= false; 
		in_option	= false;
    } 
/*
	<ITEM>
		<QUES code ="QA4562" type="multichoice">Empire Burlesque</QUES>
		<OPTION code = "1">At vero eos et accusamus et iusto odio</OPTION>
		<OPTION >dignissimos ducimus qui blanditiis praesentium voluptatum</OPTION>
		<OPTION ">deleniti atque corrupti quos dolores et quas molestias</OPTION>
		<OPTION >excepturi sint occaecati cupiditate non provident,</OPTION>
	</ITEM>   
*/  
    
    
	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		if (localName.equals("ITEM")) { 
//            this.in_item 	= true; 
    		item			= new EncuestaItem();
       }else if (localName.equals("QUES")) { 
            this.in_ques = true; 
            item.setCode(Integer.valueOf(atts.getValue("code")));
            item.setType(atts.getValue("type"));
       }else if (localName.equals("OPTION")) { 
    	   answer = new AnswerItem();
    	   answer.setValue(Integer.valueOf(atts.getValue("value")));
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
            in_ques = false; 
       }else if (localName.equals("OPTION")) { 
            in_option = false; 
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
        		 answer.setText(temita);
        		 item.addAnswer(answer);
         }
         
   }
    public Encuesta getEncuesta() { 
        return this.encuesta; 
   }    
    


		
	
    
}
