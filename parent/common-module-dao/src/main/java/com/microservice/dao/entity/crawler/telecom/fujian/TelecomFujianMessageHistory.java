package com.microservice.dao.entity.crawler.telecom.fujian;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.microservice.dao.entity.IdEntity;

/**
 * 福建电信短信记录
 * @author qizhongbin
 *
 */
@Entity
@Table(name="telecom_fujian_messagerhistory",indexes = {@Index(name = "index_telecom_fujian_messagerhistory_taskid", columnList = "taskid")})
public class TelecomFujianMessageHistory  extends IdEntity{
	/** 爬取批次号 */
	@Column(name="taskid")
	private String taskid;
	
	/**发送时间*/
	@Column(name="send_time")
	private String sendtime;
	
	/**发送号码*/
	@Column(name="send_phone")
	private String sendphone;
	
	/**对方号码*/
	@Column(name="opposite_phone")
	private String oppositephone;

	/**收发类型*/
	@Column(name="message_type")
	private String messagetype;
	
	/**业务类型*/
	@Column(name="bus_type")
	private String bustype;
	
	/**费用*/
	@Column(name="message_addr")
	private String messageaddr;
	
	public String getMessagetype() {
		return messagetype;
	}

	public void setMessagetype(String messagetype) {
		this.messagetype = messagetype;
	}

	public String getMessageaddr() {
		return messageaddr;
	}

	public void setMessageaddr(String messageaddr) {
		this.messageaddr = messageaddr;
	}

	public String getSendtime() {
		return sendtime;
	}

	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}


	public String getOppositephone() {
		return oppositephone;
	}

	public void setOppositephone(String oppositephone) {
		this.oppositephone = oppositephone;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}


	public String getBustype() {
		return bustype;
	}

	public void setBustype(String bustype) {
		this.bustype = bustype;
	}

	public String getSendphone() {
		return sendphone;
	}

	public void setSendphone(String sendphone) {
		this.sendphone = sendphone;
	}
	
}
