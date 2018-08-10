package com.myxapp.sdk.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.myxapp.sdk.base.SystemException;

/**
 * Title: Bean工具类 <br>
 * Description: 提供Bean处理的工具类<br>
 * Date: 2014年2月22日 <br>
 * Copyright (c) 2014 AILK <br>
 * 
 * @author gucl
 */
public final class BeanUtil {
	private BeanUtil() {
	}

	/**
	 * 获取VO指定属性值
	 * 
	 * @param object
	 * @param fieldName
	 * @return
	 * @author gucl
	 * @throws SystemException
	 */
	public static Object getVoFieldValue(Object object, String fieldName) throws SystemException {
		try {
			if (object == null || StringUtil.isBlank(fieldName)) {
				throw new SystemException("底层获取对象指定属性值出错,、对象为空或者指定字段为空");
			}
			String getmethodstr = "get" + StringUtil.setUpperCaseForFirstLetter(fieldName);
			Method getmethod = object.getClass().getMethod(getmethodstr);
			return getmethod.invoke(object);
		} catch (Exception ex) {
			String retMsg = ex.getCause() == null ? ex.getMessage() : ex.getCause().getMessage();
			throw new SystemException("系统错误[BeanCopyUtil.getVoFieldValue]:" + retMsg, ex);
		}
	}

	/**
	 * 设定VO指定属性值
	 * 
	 * @param object
	 * @param fieldName
	 * @param fieldValue
	 * @author gucl
	 * @throws SystemException
	 */
	public static void setVoFieldValue(Object object, String fieldName, Object fieldValue) throws SystemException {
		try {
			if (object == null || StringUtil.isBlank(fieldName)) {
				throw new SystemException("设置对象指定属性值出错,对象为空或者指定字段为空");
			}
			String setmethodstr = "set" + StringUtil.setUpperCaseForFirstLetter(fieldName);
			String getmethodstr = "get" + StringUtil.setUpperCaseForFirstLetter(fieldName);
			Method getmethod = object.getClass().getMethod(getmethodstr);
			Method setmethod = object.getClass().getMethod(setmethodstr, getmethod.getReturnType());
			setmethod.invoke(object, fieldValue);
		} catch (Exception ex) {
			String retMsg = ex.getCause() == null ? ex.getMessage() : ex.getCause().getMessage();
			throw new SystemException("系统错误[BeanCopyUtil.setVoFieldValue]:" + retMsg, ex);
		}
	}

	/**
	 * 对象是否存在指定属性
	 * 
	 * @param object
	 * @param fieldName
	 * @return
	 * @author gucl
	 */
	public static boolean hasProperty(Object object, String fieldName) {
		if (null == object || StringUtil.isBlank(fieldName))
			return false;
		Field[] fields = object.getClass().getDeclaredFields();
		if (fields == null || fields.length == 0) {
			return false;
		}
		for (Field field : fields) {
			String fieldN = field.getName();
			if (fieldN.equals(fieldName)) {
				return true;
			}
		}
		return false;
	}

	public static Object getFieldValue(Object orignVo, String fieldName) throws SystemException {
		if (orignVo == null || StringUtil.isBlank(fieldName)) {
			throw new SystemException("底层获取vo指定字段值出错,vo对象为空或者指定字段为空");
		}
		try {
			String getMethodName = "get" + StringUtil.toFirstWordUpperCase(fieldName);
			Method getmethod = orignVo.getClass().getDeclaredMethod(getMethodName);
			return getmethod.invoke(orignVo);
		} catch (Exception ex) {
			throw new SystemException("获取属性值报错", ex);
		}
	}

	/**
	 * 深度拷贝
	 * 
	 * @param destSVO
	 * @param orignSVO
	 * @author choaryzhang
	 * @throws SystemException
	 */
	public static void copyProperties(Object destSVO, Object orignSVO) throws SystemException {
		/* 1.源对象与目标对象都不能为空 */
		if (destSVO == null || orignSVO == null) {
			throw new SystemException("拷贝VO属性值出错:源对象为空或目标对象为空");
		}
		/* 2.深度拷贝 */
		try {
			PropertyUtils.copyProperties(destSVO, orignSVO);
		} catch (IllegalAccessException e) {
			throw new SystemException(e);
		} catch (NoSuchMethodException e) {
			throw new SystemException(e);
		} catch (InvocationTargetException e) {
			throw new SystemException(e);
		}
	}

	public static void copyPropertiesIgnoreNull(Object destSVO, Object orignSVO) throws SystemException {
		/* 1.源对象与目标对象都不能为空 */
		if (destSVO == null || orignSVO == null) {
			throw new SystemException("拷贝VO属性值出错:源对象为空或目标对象为空");
		}

		/* 2.深度拷贝 */
		try {
			BeanUtils.copyProperties(orignSVO, destSVO, getNullPropertyNames(orignSVO));
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}


	public static boolean hasMethod(Object obj, String methodName) {
		if (!StringUtil.isBlank(methodName)) {
			Method[] methods = obj.getClass().getDeclaredMethods();
			for (Method method : methods) {
				if (methodName.equals(method.getName())) {
					return true;
				}
			}
		}
		return false;
	}

	public static String[] getNullPropertyNames (Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for(java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null) emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

}
