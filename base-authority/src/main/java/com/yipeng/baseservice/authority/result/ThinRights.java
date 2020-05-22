package com.yipeng.baseservice.authority.result;

import lombok.Data;

/**
 * 权限 瘦对象
 */
@Data
public class ThinRights {

    /** 权限名称*/
    private String rightsName;

    /** 权限代码*/
    private String rightsCode;

    /** 应用id*/
    private String appId;

    /** 有效期*/
    private long ttl;

    /** 最近更新时间*/
    private long updateTime;
}