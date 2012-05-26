package com.sybase.supqa.tenacious;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;

public class PolicyConfig {
	private final File configFile;
	private final Document doc;
	private final Element rootElement;
	
	public PolicyConfig(String fileName){
		configFile = new File(fileName);
		try {
			doc = new Builder().build(configFile);
			rootElement = doc.getRootElement();
		} catch (Exception e) {
			throw new RuntimeException("Failed to pase "+fileName);
		}
	}
	
	public String getPolicyClassName(){
		Attribute classNameAttr = rootElement.getAttribute("class");
		return classNameAttr.getValue();
	}
	
	public String getParameterValue(String parameterName){
		Elements elements = rootElement.getChildElements();
		for(int i=0;i<elements.size(); i++){
			Element element = elements.get(i);
			if(element.getQualifiedName().equals("parameter")){
				Attribute nameAttr = element.getAttribute("name");
				if(nameAttr.getValue().equals(parameterName)){
					Attribute valueAttr = element.getAttribute("value");
					return valueAttr.getValue();
				}
			}
		}
		return null;
	}
	
	public List<String> getParameters(){
		List<String> names = new ArrayList<String>();
		Elements elements = rootElement.getChildElements();
		for(int i=0;i<elements.size(); i++){
			Element element = elements.get(i);
			names.add(element.getQualifiedName());
		}
		return names;
	}
	
	public String getCleanupHandlerClassName(){
		Elements elements = rootElement.getChildElements();
		for(int i=0;i<elements.size(); i++){
			Element element = elements.get(i);
			if(element.getQualifiedName().equals("cleanupHandler")){
				Attribute attr = element.getAttribute("class");
				return attr.getValue();
			}
		}
		return null;
	}
	
}
