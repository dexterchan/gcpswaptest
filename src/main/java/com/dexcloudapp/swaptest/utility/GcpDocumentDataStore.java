package com.dexcloudapp.swaptest.utility;

import com.dexcloudapp.swaptest.model.Convert2DocumentInterface;
import com.dexcloudapp.swaptest.model.PersistAsDocumentInterface;

public class GcpDocumentDataStore implements PersistAsDocumentInterface{

	@Override
	public void setDocumentConvertor(Convert2DocumentInterface c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean storeAsDocument() {
		// TODO Auto-generated method stub
		return false;
	}

	
//	Convert2DocumentInterface convert2DocumentHelper=null;
//	@Override
//	public void setDocumentConvertor(Convert2DocumentInterface c) {
//		// TODO Auto-generated method stub
//		convert2DocumentHelper=c;
//	}
//
//	@Override
//	public boolean storeAsDocument(Object obj) {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
