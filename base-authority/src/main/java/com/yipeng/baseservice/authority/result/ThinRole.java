package com.yipeng.baseservice.authority.result;

import lombok.Data;

/**
 * 角色 瘦对象
 */
@Data
public class ThinRole {

    /** 角色名称*/
    private String roleName;

    /** 角色代码*/
    private String roleCode;

    /** 应用id*/
    private String appId;

    /** 有效期*/
    private long ttl;

    /** 最近更新时间*/
    private long updateTime;
}