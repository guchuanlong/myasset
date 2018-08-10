package com.myxapp.sdk.sequence.client;

/**
 * ISeqClient
 * Date: 2017年2月10日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * 
 * @author
 */
public interface ISeqClient {

	/**
	 * nextValue
	 * @param sequenceName
	 * @return
	 * @author
	 */
    Long nextValue(String sequenceName);
    
    void modifySequence(String sequenceName, long nextVal);

}
