package com.yipeng.baseservice.config.constant;

import com.yipeng.framework.common.constants.CodeDesEnum;
import lombok.Getter;

/**
 * @author: yibingzhou
 */
public enum ConfigNamespace implements CodeDesEnum<Integer, ConfigNamespace> {
    DEFAULT(1, "默认空间");

    @Getter
    private Integer code;
    @Getter
    private String Description;

    ConfigNamespace(Integer code, String description) {
        this.code = code;
        Description = description;
    }

    @Override
    public ConfigNamespace[] all() {
        return values();
    }
}
