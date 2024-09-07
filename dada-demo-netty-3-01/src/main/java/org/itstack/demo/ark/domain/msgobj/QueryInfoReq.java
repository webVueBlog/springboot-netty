package org.dada.demo.ark.domain.msgobj;

/**
 * 博客：http://dada.org
 * 
 * 公众号：算法猫叔  ｛获取学习源码｝
 * Create by fuzhengwei on 2019/9/27
 */
public class QueryInfoReq {

    private Integer stateType; //类型；Feedback=>{1\2}

    public QueryInfoReq(Integer stateType) {
        this.stateType = stateType;
    }

    public Integer getStateType() {
        return stateType;
    }

    public void setStateType(Integer stateType) {
        this.stateType = stateType;
    }
}
