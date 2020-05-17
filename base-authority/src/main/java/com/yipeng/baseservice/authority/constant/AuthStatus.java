package com.yipeng.baseservice.authority.constant;

import com.yipeng.framework.core.constants.CodeDesEnum;
import lombok.Getter;

/**
 * 授权状态
 * @author: yibingzhou
 */
public enum  AuthStatus implements CodeDesEnum<Integer, AuthStatus> {

    //禁用
    DISABLED(0, "禁用"),
    //启用
    ENABLED(1, "启用"),
    //临时启用
    TEMP_ENABLED(2, "临时启用");

    @Getter
    private Integer code;
    @Getter
    private String description;

    AuthStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }


    @Override
    public AuthStatus[] all() {
        return AuthStatus.values();
    }
}
