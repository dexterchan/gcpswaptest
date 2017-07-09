package com.dexcloudapp.swaptest.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dexcloudapp.swaptest.model.Convert2DocumentInterface;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SimpleJacksonConvert2JsonHelper implements Convert2DocumentInterface{
	Logger logger = LoggerFactory.getLogger(SimpleJacksonConvert2JsonHelper.class);
	private static SimpleJacksonConvert2JsonHelper ourInstance = new SimpleJacksonConvert2JsonHelper();

    public static SimpleJacksonConvert2JsonHelper getInstance() {
        return ourInstance;
    }
    ObjectMapper _mapper = new ObjectMapper();
    
    private SimpleJacksonConvert2JsonHelper() {
    }
    
    @Override
    public String returnDocumentString(Object arg) throws Exception{
    	String jsonInString="";
            jsonInString = _mapper.writeValueAsString(arg);
            //logger.debug(jsonInString);
    	return jsonInString;
    }
    
}
