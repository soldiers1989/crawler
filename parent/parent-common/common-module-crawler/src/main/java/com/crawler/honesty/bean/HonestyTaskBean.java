package com.crawler.honesty.bean;

import java.util.Date;


/**
 * 
 * 项目名称：common-module-dao 类名称：SearchTask 类描述： 创建人：hyx 创建时间：2018年1月18日 上午11:44:00
 * 
 * @version
 */
public class HonestyTaskBean  {
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String taskid;

	private String pName;
	
	private String pCardNum;

	private String phase;// 当前步骤

	private String description;
	
	private String linkurl;
	
	private String type;//被执行人 或失信被执行人
		
	private int renum;//重试次数
	
	private Integer prioritynum; //优先级
	
	private String ipaddress;
	
	private String ipport;
	
    private Date updateTime;
	
	public int getRenum() {
		return renum;
	}

	public void setRenum(int renum) {
		this.renum = renum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLinkurl() {
		return linkurl;
	}

	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpCardNum() {
		return pCardNum;
	}

	public void setpCardNum(String pCardNum) {
		this.pCardNum = pCardNum;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public Integer getPrioritynum() {
		return prioritynum;
	}

	public void setPrioritynum(Integer prioritynum) {
		this.prioritynum = prioritynum;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getIpport() {
		return ipport;
	}

	public void setIpport(String ipport) {
		this.ipport = ipport;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	@Override
	public String toString() {
		return "HonestyTaskBean [id=" + id + ", taskid=" + taskid + ", pName=" + pName + ", pCardNum=" + pCardNum
				+ ", phase=" + phase + ", description=" + description + ", linkurl=" + linkurl + ", type=" + type
				+ ", renum=" + renum + ", prioritynum=" + prioritynum + ", ipaddress=" + ipaddress + ", ipport="
				+ ipport + ", updateTime=" + updateTime + "]";
	}

	
	

}
