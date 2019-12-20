package com.example.demo.business.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author zhupeng
 * @email 250817571@qq.com
 * @date 2019-12-17 14:33:54
 */
public class PostionDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private String type;
	//
	private String pnumber;
	//
	private BigDecimal speed;
	//
	private BigDecimal longitude;
	//
	private BigDecimal latitude;
	//
	private String img;
	//
	private Date addTime;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：
	 */
	public void setPnumber(String pnumber) {
		this.pnumber = pnumber;
	}
	/**
	 * 获取：
	 */
	public String getPnumber() {
		return pnumber;
	}
	/**
	 * 设置：
	 */
	public void setSpeed(BigDecimal speed) {
		this.speed = speed;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getSpeed() {
		return speed;
	}
	/**
	 * 设置：
	 */
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getLongitude() {
		return longitude;
	}
	/**
	 * 设置：
	 */
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getLatitude() {
		return latitude;
	}
	/**
	 * 设置：
	 */
	public void setImg(String img) {
		this.img = img;
	}
	/**
	 * 获取：
	 */
	public String getImg() {
		return img;
	}
	/**
	 * 设置：
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	/**
	 * 获取：
	 */
	public Date getAddTime() {
		return addTime;
	}
}
