package com.microservice.dao.entity.crawler.pbccrc;

import com.microservice.dao.entity.IdEntity;
import org.codehaus.jackson.annotate.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 购房贷款记录详细信息析记录
 * Created by zmy on 2017/12/26.
 */
@Entity
@Table(name="housing_loan_record_detail")
public class HousingLoanRecordDetail extends AbstractEntity implements Serializable{
    private static final long serialVersionUID = 3640304384109799522L;

    private String mapping_id;  //uuid 唯一标识
    private String report_no;   //人行征信报告编号

    private Long recordDetail_autoId;   //对应于creditRecordDetails中的auto_id

    @JsonBackReference
    public CreditRecordDetail creditRecordDetail;

    private String grant_date;   //	贷款发放的时间
    private String bank_name;   //	发放贷款银行的名称
    private String currency;   //	贷款币种
    private String loan_amount;   //	购房贷款总金额
    private String loan_item;   //	贷款对象
    private String expiration_date;   //	购房贷款到期日
    private String cutoff_date;   //	本征信报告获取本该购房贷款信息的最后时间（截止日期）
    private String is_closeout;   //	购房贷款是否已结清
    private String remain_balance;   //	购房贷款余额
    private String is_overdue;   //	购房贷款是否有逾期
    private String overdue_month;   //	购房贷款有逾期的月数
    private String overdue_amount;   //	逾期金额
    private String is_nintydays_overdue;   //	购房贷款是否有超过90天的逾期
    private String nintydays_oversue_month;   //	购房逾期超过90天的月数
    private String transfer;   //	是否已转出
    private String transfer_date;   //	转出日期

    private String status;      //账户状态
    private String delqL5yAmt;  //最近五年逾期次数
    private String delqL5y90dayAmt; //最近五年90天以上逾期次数
    private  String  settleDate;   //结清年月

    @OneToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="recordDetail_autoId")
    public CreditRecordDetail getCreditRecordDetail() {
        return creditRecordDetail;
    }

    public void setCreditRecordDetail(CreditRecordDetail creditRecordDetail) {
        this.creditRecordDetail = creditRecordDetail;
    }

    @Transient
    public Long getRecordDetail_autoId() {
        if (creditRecordDetail != null) {
            return creditRecordDetail.getAuto_id();
        }
        return recordDetail_autoId;
    }

    public void setRecordDetail_autoId(Long recordDetail_autoId) {
        this.recordDetail_autoId = recordDetail_autoId;
    }

    public String getMapping_id() {
        return mapping_id;
    }

    public void setMapping_id(String mapping_id) {
        this.mapping_id = mapping_id;
    }

    public String getReport_no() {
        return report_no;
    }

    public void setReport_no(String report_no) {
        this.report_no = report_no;
    }

    public String getGrant_date() {
        return grant_date;
    }

    public void setGrant_date(String grant_date) {
        this.grant_date = grant_date;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(String loan_amount) {
        this.loan_amount = loan_amount;
    }

    public String getLoan_item() {
        return loan_item;
    }

    public void setLoan_item(String loan_item) {
        this.loan_item = loan_item;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }

    public String getCutoff_date() {
        return cutoff_date;
    }

    public void setCutoff_date(String cutoff_date) {
        this.cutoff_date = cutoff_date;
    }

    public String getIs_closeout() {
        return is_closeout;
    }

    public void setIs_closeout(String is_closeout) {
        this.is_closeout = is_closeout;
    }

    public String getRemain_balance() {
        return remain_balance;
    }

    public void setRemain_balance(String remain_balance) {
        this.remain_balance = remain_balance;
    }

    public String getIs_overdue() {
        return is_overdue;
    }

    public void setIs_overdue(String is_overdue) {
        this.is_overdue = is_overdue;
    }

    public String getOverdue_month() {
        return overdue_month;
    }

    public void setOverdue_month(String overdue_month) {
        this.overdue_month = overdue_month;
    }

    public String getOverdue_amount() {
        return overdue_amount;
    }

    public void setOverdue_amount(String overdue_amount) {
        this.overdue_amount = overdue_amount;
    }

    public String getIs_nintydays_overdue() {
        return is_nintydays_overdue;
    }

    public void setIs_nintydays_overdue(String is_nintydays_overdue) {
        this.is_nintydays_overdue = is_nintydays_overdue;
    }

    public String getNintydays_oversue_month() {
        return nintydays_oversue_month;
    }

    public void setNintydays_oversue_month(String nintydays_oversue_month) {
        this.nintydays_oversue_month = nintydays_oversue_month;
    }

    public String getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }

    public String getTransfer_date() {
        return transfer_date;
    }

    public void setTransfer_date(String transfer_date) {
        this.transfer_date = transfer_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelqL5yAmt() {
        return delqL5yAmt;
    }

    public void setDelqL5yAmt(String delqL5yAmt) {
        this.delqL5yAmt = delqL5yAmt;
    }

    public String getDelqL5y90dayAmt() {
        return delqL5y90dayAmt;
    }

    public void setDelqL5y90dayAmt(String delqL5y90dayAmt) {
        this.delqL5y90dayAmt = delqL5y90dayAmt;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    @Override
    public String toString() {
        return "HousingLoanRecordDetail{" +
                "mapping_id='" + mapping_id + '\'' +
                ", report_no='" + report_no + '\'' +
                ", recordDetail_autoId=" + recordDetail_autoId +
                ", creditRecordDetail=" + creditRecordDetail +
                ", grant_date='" + grant_date + '\'' +
                ", bank_name='" + bank_name + '\'' +
                ", currency='" + currency + '\'' +
                ", loan_amount='" + loan_amount + '\'' +
                ", loan_item='" + loan_item + '\'' +
                ", expiration_date='" + expiration_date + '\'' +
                ", cutoff_date='" + cutoff_date + '\'' +
                ", is_closeout='" + is_closeout + '\'' +
                ", remain_balance='" + remain_balance + '\'' +
                ", is_overdue='" + is_overdue + '\'' +
                ", overdue_month='" + overdue_month + '\'' +
                ", overdue_amount='" + overdue_amount + '\'' +
                ", is_nintydays_overdue='" + is_nintydays_overdue + '\'' +
                ", nintydays_oversue_month='" + nintydays_oversue_month + '\'' +
                ", transfer='" + transfer + '\'' +
                ", transfer_date='" + transfer_date + '\'' +
                ", status='" + status + '\'' +
                ", delqL5yAmt='" + delqL5yAmt + '\'' +
                ", delqL5y90dayAmt='" + delqL5y90dayAmt + '\'' +
                ", settleDate='" + settleDate + '\'' +
                '}';
    }
}
