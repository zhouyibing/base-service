package com.yipeng.baseservice.config.constant;

import com.yipeng.framework.common.constants.CodeDesEnum;
import lombok.Getter;

/**
 * 配置源(配置信息存储源)
 * @author: yibingzhou
 */
public enum ConfigSource implements CodeDesEnum<Integer, ConfigSource> {
    MYSQL(1, "mysql"),REDIS(2, "redis"),CONSUL(3, "consul");

    @Getter
    private Integer code;
    @Getter
    private String description;

    ConfigSource(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public ConfigSource[] all() {
        return values();
    }
}
