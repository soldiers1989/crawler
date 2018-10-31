package com.microservice.dao.entity.crawler.bank.cmbchina;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.microservice.dao.entity.IdEntity;

/**
 * html源码
 * @author tz
 *
 */
@Entity
@Table(name = "cmbchina_creditcard_html" ,indexes = {@Index(name = "index_cmbchina_creditcard_html_taskid", columnList = "taskid")})
public class CmbChinaCreditCardHtml extends IdEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4658347137471757302L;

	/**
	 * taskid  uuid 前端通过uuid访问状态结果
	 */
	private String taskid;
	
	private String type;
	private String pageCount;	
	private String url;	
	private String html;
	
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPageCount() {
		return pageCount;
	}
	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}
	
	@Column(columnDefinition="text")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(columnDefinition="text")
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	@Override
	public String toString() {
		return "InsuranceShanghaiHtml [taskid=" + taskid + ", type=" + type + ", pageCount=" + pageCount + ", url="
				+ url + ", html=" + html + "]";
	}
	public CmbChinaCreditCardHtml(String taskid, String type, String pageCount, String url, String html) {
		super();
		this.taskid = taskid;
		this.type = type;
		this.pageCount = pageCount;
		this.url = url;
		this.html = html;
	}
	public CmbChinaCreditCardHtml() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
