package com.dexcloudapp.swaptest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dexcloudapp.swaptest.model.Convert2DocumentInterface;
import com.dexcloudapp.swaptest.model.test.SwapTrade1;
import com.dexcloudapp.swaptest.randomizer.RandomSwapCreator;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class testDataStore extends TestCase {

    Logger logger = LoggerFactory.getLogger(testSwap.class);
    public static TestSuite suite() {
        return new TestSuite(testSwap.class);
    }

    @Test
    public void testSwapStore() {
    	logger.debug("Run test swap");
        
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeanConfig.xml");
        
        RandomSwapCreator creator = context.getBean("SwapRandomizer",RandomSwapCreator.class);
        SwapTrade1 s=creator.prepareRandomIRS(1000000, "USD", "LIBOR", 3.0,true,false);
        
        
        //ObjectMapper mapper = new ObjectMapper();
        try{
        	Convert2DocumentInterface jsonHelper = context.getBean("Convert2DocumentHelper",Convert2DocumentInterface.class);
        	String jsonInString =jsonHelper.returnDocumentString( s);
//        String jsonInString = mapper.writeValueAsString(s);
        	logger.debug(jsonInString);
        	
        	/*
        	// Instantiates a client
            Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
            
         // The kind for the new entity
            String kind = "Swap";
            // The name/ID for the new entity
            String name = "sampleswap1";
            // The Cloud Datastore key for the new entity
            Key taskKey = datastore.newKeyFactory().setKind(kind).newKey(name);
            
         // Prepares the new entity
            Entity swap = Entity.newBuilder(taskKey)
                .set("raw", jsonInString)
                .build();

            // Saves the entity
            datastore.put(swap);

            System.out.printf("Saved %s: %s%n", swap.getKey().getName(), swap.getString("raw"));

            //Retrieve entity
            Entity retrieved = datastore.get(taskKey);

            System.out.printf("Retrieved %s: %s%n", taskKey.getName(), retrieved.getString("raw"));
*/
        	
        }catch(Exception e){e.printStackTrace();}
        
    	
    }

}
