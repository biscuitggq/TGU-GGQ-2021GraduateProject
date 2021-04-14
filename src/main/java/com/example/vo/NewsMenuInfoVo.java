package com.example.vo;

import com.example.entity.NewsMenuInfo;

public class NewsMenuInfoVo extends NewsMenuInfo {

    private String subName;
    private Integer praiseCount;
    private Integer collectCount;

    public Integer getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Integer praiseCount) {
        this.praiseCount = praiseCount;
    }

    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    public String getSubName() {
        return subName;
    }
    public void setSubName(String subName) {
        this.subName = subName;
    }

}
