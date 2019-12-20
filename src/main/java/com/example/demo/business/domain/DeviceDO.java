package com.example.demo.business.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author zhupeng
 * @email 250817571@qq.com
 * @date 2019-12-17 14:33:39
 */
public class DeviceDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private String nodeName;
	//
	private String ipAddress;
	//
	private String status;
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
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	/**
	 * 获取：
	 */
	public String getNodeName() {
		return nodeName;
	}
	/**
	 * 设置：
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	/**
	 * 获取：
	 */
	public String getIpAddress() {
		return ipAddress;
	}
	/**
	 * 设置：
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：
	 */
	public String getStatus() {
		return status;
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
