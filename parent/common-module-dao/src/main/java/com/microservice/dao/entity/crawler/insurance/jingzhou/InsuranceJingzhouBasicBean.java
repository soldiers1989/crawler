package com.microservice.dao.entity.crawler.insurance.jingzhou;

import com.microservice.dao.entity.IdEntity;

import java.io.Serializable;

public class InsuranceJingzhouBasicBean extends IdEntity  implements Serializable {
    protected String taskid;

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }
}
