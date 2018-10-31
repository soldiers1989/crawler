package com.microservice.dao.entity.crawler.insurance.chongqing;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.microservice.dao.entity.IdEntity;
/**
 * 重庆社保工伤
 * @author zhaochunxiang
 *
 */
@Entity
@Table(name="insurance_chongqing_injury",indexes = {@Index(name = "index_insurance_chongqing_injury_taskid", columnList = "taskid")})
public class InsuranceChongqingInjury extends IdEntity {

	private String name; // 姓名
	private String payMonth; // 缴费年月
	private String companyName; // 单位名称
	private String payPlace; // 参保地
	private String payBase; // 缴费基数(元)
	private String payMark; // 缴费标记
	private String personNumber;//个人编号	
	private String idNum;//身份证号
	private String paymentType;//缴费类型
	private String companyNum;//单位编号
	private String taskid;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPayMonth() {
		return payMonth;
	}
	public void setPayMonth(String payMonth) {
		this.payMonth = payMonth;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getPayPlace() {
		return payPlace;
	}
	public void setPayPlace(String payPlace) {
		this.payPlace = payPlace;
	}
	public String getPayBase() {
		return payBase;
	}
	public void setPayBase(String payBase) {
		this.payBase = payBase;
	}
	public String getPayMark() {
		return payMark;
	}
	public void setPayMark(String payMark) {
		this.payMark = payMark;
	}
	
	public String getPersonNumber() {
		return personNumber;
	}
	public void setPersonNumber(String personNumber) {
		this.personNumber = personNumber;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getCompanyNum() {
		return companyNum;
	}
	public void setCompanyNum(String companyNum) {
		this.companyNum = companyNum;
	}
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public InsuranceChongqingInjury() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InsuranceChongqingInjury(String name, String payMonth, String companyName, String payPlace, String payBase,
			String payMark, String personNumber, String idNum, String paymentType, String companyNum, String taskid) {
		super();
		this.name = name;
		this.payMonth = payMonth;
		this.companyName = companyName;
		this.payPlace = payPlace;
		this.payBase = payBase;
		this.payMark = payMark;
		this.personNumber = personNumber;
		this.idNum = idNum;
		this.paymentType = paymentType;
		this.companyNum = companyNum;
		this.taskid = taskid;
	}
	@Override
	public String toString() {
		return "InsuranceChongqingInjury [name=" + name + ", payMonth=" + payMonth + ", companyName=" + companyName
				+ ", payPlace=" + payPlace + ", payBase=" + payBase + ", payMark=" + payMark + ", personNumber="
				+ personNumber + ", idNum=" + idNum + ", paymentType=" + paymentType + ", companyNum=" + companyNum
				+ ", taskid=" + taskid + "]";
	}
	
}
