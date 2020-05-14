package com.thinkgem.jeesite.modules.myasset.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

/*
 * create by haojf
 */
public class DateConverter implements Converter<String, Date>{

	@Override
	public Date convert(String source) {
		 Date t=null;
	        if(StringUtils.isNotBlank(source)){
	        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
	        	try {
					t=sdf.parse(source);
				} catch (ParseException e) {
					e.printStackTrace();
					SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
					try {
						t=sdf2.parse(source);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
	        }
	        return t;
	}

}
