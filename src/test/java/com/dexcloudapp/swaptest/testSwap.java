package com.dexcloudapp.swaptest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dexcloudapp.swaptest.model.Convert2DocumentInterface;
import com.dexcloudapp.swaptest.model.test.SwapTrade1;
import com.dexcloudapp.swaptest.randomizer.RandomSwapCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Created by dexter on 7/8/17.
 */
public class testSwap extends TestCase {

    Logger logger = LoggerFactory.getLogger(testSwap.class);
    public static TestSuite suite() {
        return new TestSuite(testSwap.class);
    }


    @Test
    public void testSwapCreation() {
        logger.debug("Run test swap");
        
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeanConfig.xml");
        
        RandomSwapCreator creator = context.getBean("SwapRandomizer",RandomSwapCreator.class);
        SwapTrade1 s=creator.prepareRandomIRS(1000000, "USD", "LIBOR", 3.0,true);
        
        
        //ObjectMapper mapper = new ObjectMapper();
        try{
        	Convert2DocumentInterface jsonHelper = context.getBean("Convert2DocumentHelper",Convert2DocumentInterface.class);
        	String jsonInString =jsonHelper.returnDocumentString( s);
//        String jsonInString = mapper.writeValueAsString(s);
        	logger.debug(jsonInString);
        }catch(Exception e){e.printStackTrace();}
        
        
        assert(true);

    }
}
