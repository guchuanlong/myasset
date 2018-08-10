package com.myxapp.sdk.base.vo;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * 参数基础类. Date: 2017年2月22日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * 
 * @author gucl
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	public BaseInfo() {
		super();
		init();
	}

	private void init() {
		// 当没有时，自动生成一个。发生在web新建对象时。当到服务提供者端时，已经有了。
		if (null == traceId || "".equals(traceId))
			traceId = UUID.randomUUID().toString().replaceAll("\\-", "").toUpperCase();
	}

	public BaseInfo(PageArg page) {
		super();
		init();
		this.page = page;
	}

	public BaseInfo(int pageNum, int pageSize) {
		super();
		init();
		this.page = new PageArg(pageNum, pageSize);
	}

	/**
	 * traceId，必填
	 */
	private String traceId;

	private PageArg page;
	/**
	 * 租户Id，必填
	 */
	private String tenantId;
	/**
	 * 租户密码，可选
	 */
	private String tenantPwd;

	/**
	 * 工号
	 */
	private String operId;

	/**
	 * 省分代码
	 */
	private String provinceCode;

	/**
	 * 地市代码
	 */
	private String cityCode;

	private String countyCode;

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantPwd() {
		return tenantPwd;
	}

	public void setTenantPwd(String tenantPwd) {
		this.tenantPwd = tenantPwd;
	}

	public PageArg getPage() {
		return page;
	}

	public void setPage(PageArg page) {
		this.page = page;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

}
