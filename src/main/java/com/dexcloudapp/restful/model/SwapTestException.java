package com.dexcloudapp.restful.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND,reason="SWAP creation failed")
public class SwapTestException extends Exception {
	public SwapTestException(String msg){
		super(msg);
	}
}
