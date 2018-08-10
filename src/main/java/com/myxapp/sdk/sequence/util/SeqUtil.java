package com.myxapp.sdk.sequence.util;

import com.myxapp.sdk.sequence.factory.SeqClientFactory;
import com.myxapp.sdk.util.StringUtil;

/**
 * sequence工具类
 * Date: 2017年2月10日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * 
 * @author
 */
public final class SeqUtil {

    private SeqUtil() {

    }

    /**
     * 获取下一个Id
     * @param seqName
     * @return
     * @author
     */
    public static Long getNewId(String seqName) {
        return SeqClientFactory.getSeqClient().nextValue(seqName);
    }

    /**
     * 获取新的sequence
     * @param seqName
     * @param seqLen
     * @return
     * @author
     */
    public static String getNewId(String seqName, int seqLen) {
        Long newId = getNewId(seqName);
        String seqStr = StringUtil.toString(newId);
        while (seqStr.length() < seqLen) {
            seqStr = "0000000" + seqStr;
        }
        return seqStr.substring(seqStr.length() - seqLen);
    }
    
    /**
     * 获取下一个Id
     * @param seqName
     * @return
     * @author
     */
    public static String getNewId36Hex(String seqName) {
    	Long newId=SeqClientFactory.getSeqClient().nextValue(seqName);
    	String seqStr = Long.toString(newId,36).toUpperCase();//36进制   0-9A-Z
        return seqStr;
    }
    
    
    /**
     * 获取36进制的序列（由0-9 A-Z组成）
     * @param seqName
     * @param seqLen
     * @return
     * @author gucl
     */
    public static String getNewId36Hex(String seqName, int seqLen) {
        Long newId = getNewId(seqName);
        String seqStr = Long.toString(newId,36).toUpperCase();//36进制   0-9A-Z
        while (seqStr.length() < seqLen) {
            seqStr = "0000000" + seqStr;
        }
        return seqStr.substring(seqStr.length() - seqLen);
    }
    
    
    /**
     * 重置序列的值
     * @param seqName
     * @return
     * @author
     */
    public static void modifySequence(String seqName, long nextVal) {
        SeqClientFactory.getSeqClient().modifySequence(seqName, nextVal);
    }

}
