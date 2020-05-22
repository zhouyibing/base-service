package com.yipeng.baseservice.authority.result;

import lombok.Data;

import java.util.List;

/**
 * 用户角色
 * @author: yibingzhou
 */

@Data
public class UserPermissions {

    /** 用户id */
    private Long userId;

    /** 角色列表*/
    private List<ThinRole> roles;

    /** 权限列表（包含角色权限，特殊权限）*/
    private List<ThinRights> rights;
}
