package com.cooksys.assessment;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Elements")
public class Elements {
  
	@XmlElement
	  List<String> parts;
	
	public List<String> getParts(){
		if(parts == null){
			parts = new ArrayList<String>();
		}
		return this.parts;  
	}
}
