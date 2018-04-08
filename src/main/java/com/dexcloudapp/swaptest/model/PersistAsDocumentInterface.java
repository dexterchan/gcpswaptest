package com.dexcloudapp.swaptest.model;

public interface PersistAsDocumentInterface {
	public void setDocumentConvertor(Convert2DocumentInterface c);
	public boolean storeAsDocument();
}
