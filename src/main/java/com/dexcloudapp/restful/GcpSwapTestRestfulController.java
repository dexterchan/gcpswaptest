package com.dexcloudapp.restful;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dexcloudapp.restful.model.MonoSwapRequest;
import com.dexcloudapp.restful.model.SwapTestException;
import com.dexcloudapp.swaptest.model.Convert2DocumentInterface;
import com.dexcloudapp.swaptest.model.test.SwapTrade1;
import com.dexcloudapp.swaptest.randomizer.RandomSwapCreator;

import io.swagger.annotations.Api;


@RestController
@Api(value = "API to create swap"
		+ " parameters", 
	description = "This API provides the capability to test swap"
			, produces = "application/json")
public class GcpSwapTestRestfulController {
	Logger logger = LoggerFactory.getLogger(GcpSwapTestRestfulController.class);
	
	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public String greeting(){
		return "Hello!";
	}
	
	//@ApiOperation(value = "Create random swap", produces = "application/json")
	@RequestMapping(value = "/randomirs", method = RequestMethod.POST)
    public ResponseEntity <  SwapTrade1 > createSingleRandomIRS(@RequestBody MonoSwapRequest monoswapRequest) throws SwapTestException{
		SwapTrade1 s = null;
		
		RandomSwapCreator creator = SwaptestApplication.context.getBean("SwapRandomizer",RandomSwapCreator.class);
        s=creator.prepareRandomIRS(monoswapRequest.ntl, monoswapRequest.ccy, monoswapRequest.index, monoswapRequest.rate,monoswapRequest.randomNtl);
        
        try{
        	Convert2DocumentInterface jsonHelper = SwaptestApplication.context.getBean("Convert2DocumentHelper",Convert2DocumentInterface.class);
        	String jsonInString =jsonHelper.returnDocumentString( s);
//        String jsonInString = mapper.writeValueAsString(s);
        	logger.debug(jsonInString);
        }catch(Exception e){
        	throw new SwapTestException(e.getMessage());
        }
		
        return new ResponseEntity<SwapTrade1> (s,HttpStatus.OK);
    }
	
	@ExceptionHandler(SwapTestException.class)
	public ResponseEntity<SwapTestException> rulesForFxQuoteNotFound(HttpServletRequest req, Exception e) 
	{
		SwapTestException fe=null;
		if(e instanceof SwapTestException){
			fe=(SwapTestException)e;
		}else{
			fe = new SwapTestException("Creation problem");
		}
		return new ResponseEntity<SwapTestException>(fe, HttpStatus.NOT_FOUND);
	}
	
}
