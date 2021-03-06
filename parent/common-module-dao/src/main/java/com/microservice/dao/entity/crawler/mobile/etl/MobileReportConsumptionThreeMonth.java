package com.microservice.dao.entity.crawler.mobile.etl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.microservice.dao.entity.IdEntity;

@Entity
@Table(name="mobile_report_consumption_three_month",indexes = {@Index(name = "index_mobile_report_consumption_three_month_taskid", columnList = "taskId")})

public class MobileReportConsumptionThreeMonth extends IdEntity implements Serializable{
	
	private static final long serialVersionUID = 5133365472766002696L;
	
	@Column(name="task_id")
	private String taskId;	//唯一标识
	private String callPay;
	private String monthFlow;
	private String netFlow;
	private String rechangeAmount;
	
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getCallPay() {
		return callPay;
	}
	public void setCallPay(String callPay) {
		this.callPay = callPay;
	}
	public String getMonthFlow() {
		return monthFlow;
	}
	public void setMonthFlow(String monthFlow) {
		this.monthFlow = monthFlow;
	}
	public String getNetFlow() {
		return netFlow;
	}
	public void setNetFlow(String netFlow) {
		this.netFlow = netFlow;
	}
	public String getRechangeAmount() {
		return rechangeAmount;
	}
	public void setRechangeAmount(String rechangeAmount) {
		this.rechangeAmount = rechangeAmount;
	}
	@Override
	public String toString() {
		return "MobileReportConsumptionThreeMonth [taskId=" + taskId + ", callPay=" + callPay + ", monthFlow="
				+ monthFlow + ", netFlow=" + netFlow + ", rechangeAmount=" + rechangeAmount + "]";
	}
	
}
