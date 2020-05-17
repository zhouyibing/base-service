package com.yipeng.baseservice.config.constant;

import com.yipeng.framework.core.constants.CodeDesEnum;
import lombok.Getter;

/**
 * @author: yibingzhou
 */
public enum ConfigNamespace implements CodeDesEnum<Integer, ConfigNamespace> {
    //默认空间
    DEFAULT(1, "默认空间");

    @Getter
    private Integer code;

    @Getter
    private String description;

    ConfigNamespace(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public ConfigNamespace[] all() {
        return values();
    }
}
