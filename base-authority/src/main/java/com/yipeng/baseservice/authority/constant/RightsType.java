package com.yipeng.baseservice.authority.constant;

import com.yipeng.framework.core.constants.CodeDesEnum;
import lombok.Getter;

/**
 * 权限类型
 * @author: yibingzhou
 */
public enum RightsType implements CodeDesEnum<Integer, RightsType> {

    //资源型
    RESOURCE(1, "资源型"),
    //功能型
    FUNCTION(2, "功能型"),
    //数据型
    DATA(3, "数据型");

    @Getter
    private Integer code;
    @Getter
    private String description;

    RightsType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }


    @Override
    public RightsType[] all() {
        return RightsType.values();
    }
}
