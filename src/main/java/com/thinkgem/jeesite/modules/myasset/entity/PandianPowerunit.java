/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 供电单位Entity
 * @author gucl
 * @version 2019-05-11
 */
public class PandianPowerunit extends DataEntity<PandianPowerunit> {
	
	private static final long serialVersionUID = 1L;
	private String cityCode;		// city_code
	private String cityName;		// city_name
	private String powerunitCode;		// powerunit_code
	private String powerunitName;		// powerunit_name
	private String batchUuid;		// batch_uuid
	private String powerunitPandianFlag;		// 盘点标识
	
	public PandianPowerunit() {
		super();
	}

	public PandianPowerunit(String id){
		super(id);
	}

	@Length(min=0, max=50, message="city_code长度必须介于 0 和 50 之间")
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	@Length(min=0, max=20, message="powerunit_code长度必须介于 0 和 20 之间")
	public String getPowerunitCode() {
		return powerunitCode;
	}

	public void setPowerunitCode(String powerunitCode) {
		this.powerunitCode = powerunitCode;
	}
	
	@Length(min=0, max=255, message="powerunit_name长度必须介于 0 和 255 之间")
	public String getPowerunitName() {
		return powerunitName;
	}

	public void setPowerunitName(String powerunitName) {
		this.powerunitName = powerunitName;
	}
	
	@Length(min=0, max=50, message="batch_uuid长度必须介于 0 和 50 之间")
	public String getBatchUuid() {
		return batchUuid;
	}

	public void setBatchUuid(String batchUuid) {
		this.batchUuid = batchUuid;
	}
	
	@Length(min=0, max=11, message="盘点标识长度必须介于 0 和 11 之间")
	public String getPowerunitPandianFlag() {
		return powerunitPandianFlag;
	}

	public void setPowerunitPandianFlag(String powerunitPandianFlag) {
		this.powerunitPandianFlag = powerunitPandianFlag;
	}
	
}