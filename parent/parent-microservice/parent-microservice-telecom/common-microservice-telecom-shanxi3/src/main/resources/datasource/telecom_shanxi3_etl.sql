CREATE OR REPLACE FUNCTION "public"."telecom_shanxi3_etl"("taskid" text)
  RETURNS "pg_catalog"."text" AS $BODY$   
BEGIN  
  DECLARE
		this_id text;
		p_etl_status text DEFAULT 'success';
		p_etl_error_detail text;
		p_etl_error_content text;
		
	BEGIN
		this_id = taskid;


--清除此taskid的数据

		delete from pro_mobile_bill_info where task_id = this_id;
		delete from pro_mobile_call_info where task_id = this_id;
		delete from pro_mobile_pay_info where task_id = this_id;
		delete from pro_mobile_service_info where task_id = this_id;
		delete from pro_mobile_sms_info where task_id = this_id;
		delete from pro_mobile_user_info where task_id = this_id;



-------------------------开始插入数据--------------------------------



---用户基本信息

		INSERT INTO pro_mobile_user_info
		(
			resource,task_id,createtime,carrier,basic_user_name,basic_id_num,
			city,province,phone_num,contact_num,user_name,address,cus_level,
			cur_balance,points,package_name
		)
			select 
				'telecom_shanxi3_userinfo:'|| a.id::TEXT as resource,
				a.taskid as task_id,
				a.createtime as createtime,
				'中国电信' as carrier,
				e.name as basic_user_name,
				e.idnum as basic_id_num,
				b.city as city,
				b.province as province,
				b.phonenum as phone_num,
				a.contactnum as contact_num,
				a.cusname as user_name,
				a.detailaddress as address,
				f.item_name as cus_level,
				d.month_charge as cur_balance,
				d.myjifen as points,
				g.businessname as package_name
			from 
				telecom_shanxi3_userinfo a left join task_mobile b
				on a.taskid = b.taskid left join telecom_common_starlevel c
				on a.taskid = c.taskid left join telecom_common_pointsandcharges d
				on a.taskid = d.taskid left join basic_user e
				on b.basic_user_id = e.id left join userinfo_cuslevel_item_code f
				on c.membership_level = f.source_name left join telecom_shanxi3_business g
				on a.taskid = g.taskid
			where a.taskid = this_id;

--缴费信息

		INSERT INTO pro_mobile_pay_info
		(
			resource,task_id,createtime,payfee,paytime,paymonth,payway
		)
			select 
				'telecom_shanxi3_chargeinfo:' || a.id::text as resource,
				a.taskid as task_id,
				a.createtime as createtime,
				a.chargemoney as payfee,
				to_char(a.chargetime::TIMESTAMP,'yyyy-mm-dd') as paytime,
				to_char(a.chargetime::TIMESTAMP,'yyyy-mm') as paymonth,
				b.item_name as payway
			from telecom_shanxi3_chargeinfo a left join payinfo_paytype_item_code b
				on a.chargeway = b.source_name
			where a.taskid = this_id;

--短信信息

		INSERT INTO pro_mobile_sms_info
		(
			resource,task_id,createtime,host_num,other_num,fee,sms_type,sms_way,send_time
		)
			select 
				'telecom_shanxi3_smsrecord:' || a.id::text as resource,
				a.taskid as task_id,
				a.createtime as createtime,
				b.phonenum as host_num,
				a.othernum as other_num,
				a.totalcost as fee,
				c.item_name as sms_type,
				null as sms_way,
				a.sendtime as send_time
			from telecom_shanxi3_smsrecord a left join task_mobile b
				on a.taskid = b.taskid left join smsinfo_smstype_item_code c
				on a.communicationtype = c.source_name 
			where a.taskid = this_id;

--套餐（服务）信息

--		INSERT INTO pro_mobile_service_info
--		(
--			resource,task_id,createtime,service_name
--		)
--			select
--				'telecom_shanxi3_business:'|| a.id::TEXT as resource,
--				a.taskid as task_id,
--				a.createtime as createtime,
--				a.businessname as service_name
--			from telecom_shanxi3_business a
--			where a.taskid = this_id;

--通话记录信息

		INSERT INTO pro_mobile_call_info
		(
			resource,task_id,createtime,phone_num,his_num,fee,call_location,call_time,call_type,charge_type,
			call_duration
		)
			select 
				'telecom_shanxi3_callrecord:'||a.id::text as resource,
				a.taskid as task_id,
				a.createtime as createtime,
				b.phonenum as phone_num,
--				case when a.called_num = b.phonenum then a.calling_num else a.called_num end as his_num,
				a.othernum as his_num,
				a.totalcharge as fee,
				a.calladdress as call_location,
				a.starttime as call_time,
				c.item_name as call_type,
				d.item_name as charge_type,
				handle_timeformat('shanxi3',a.costtime) as call_duration
			from telecom_shanxi3_callrecord a left join task_mobile b
				on a.taskid = b.taskid left join callinfo_calltype_item_code c
				on a.calltype = c.source_name left join callinfo_chargetype_item_code d
				on a.linktype = d.source_name 
			where a.taskid = this_id;

--月账单信息

		INSERT INTO pro_mobile_bill_info
		(
			task_id,sum_fee,basic_fee,message_fee,normal_call_fee,roam_call_fee,flow_fee,
			function_fee,other_fee,bill_month
		)
			select 
				a.taskid as task_id,
				sum(a.totalcost::NUMERIC) as sum_fee,
				sum(case when b.item_name = '基本套餐费' then a.totalcost::NUMERIC else 0 end)::text as basic_fee,
				sum(case when b.item_name = '短信彩信费' then a.totalcost::NUMERIC else 0 end)::text as message_fee,
				sum(case when b.item_name = '国内通话费' then a.totalcost::NUMERIC else 0 end)::text as normal_call_fee,
				sum(case when b.item_name = '异地通话费' then a.totalcost::NUMERIC else 0 end)::text as roam_call_fee,
				sum(case when b.item_name = '互联网流量费' then a.totalcost::NUMERIC else 0 end)::text as flow_fee,
				sum(case when b.item_name = '业务功能费' then a.totalcost::NUMERIC else 0 end)::text as function_fee,
				sum(case when b.item_name = '其他费用' then a.totalcost::NUMERIC else 0 end)::text as other_fee,
				substr(a.countmonth,1,4) ||'-'|| substr(a.countmonth,5,2) as bill_month
--				a.yearmonth as bill_month
			from telecom_shanxi3_monthbill a left join billinfo_feetype_item_code b
				on a.expensename = source_name
			where a.taskid = this_id
			group by 
				substr(a.countmonth,1,4) ||'-'|| substr(a.countmonth,5,2),
				a.taskid;

--更新此任务状态

	--		UPDATE task_mobile a set a.etltime = now(),a.etl_status = p_etl_status where a.taskid = this_id;

			RETURN p_etl_status;
		
--异常处理
	EXCEPTION
		WHEN QUERY_CANCELED THEN 
--获取错误详情
			GET STACKED DIAGNOSTICS p_etl_error_detail = MESSAGE_TEXT;
			GET STACKED DIAGNOSTICS p_etl_error_content = PG_EXCEPTION_CONTEXT;
--记录错误到task表
--			UPDATE task_mobile a set a.etl_status = p_etl_status where a.taskid = this_id;
--返回失败
			p_etl_status = p_etl_error_content ||' : '|| p_etl_error_detail;

			RETURN p_etl_status;
			

		WHEN OTHERS THEN 
--获取错误详情
			GET STACKED DIAGNOSTICS p_etl_error_detail = MESSAGE_TEXT;
			GET STACKED DIAGNOSTICS p_etl_error_content = PG_EXCEPTION_CONTEXT;
--记录错误到task表
--			UPDATE task_mobile a set a.etl_status = p_etl_status where a.taskid = this_id;
--返回失败
			p_etl_status = p_etl_error_content ||' : '|| p_etl_error_detail;

			RETURN p_etl_status;

	END;
END;   
$BODY$
  LANGUAGE 'plpgsql' VOLATILE COST 100
;

--ALTER FUNCTION "public"."telecom_shanxi3_etl"("taskid" text) OWNER TO "TXDB";
;;--请不要删除这一行，前面两个分好和application.yml文件中配置的spring.datasource.separator的分隔符对应