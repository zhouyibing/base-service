package com.yipeng.baseservice.config.constant;

import com.yipeng.framework.core.constants.CodeDesEnum;
import lombok.Getter;

/**
 * 配置源(配置信息存储源)
 * @author: yibingzhou
 */
public enum ConfigSource implements CodeDesEnum<Integer, ConfigSource> {
    //mysql存储源
    MYSQL(1, "mysql"),
    //redis存储源
    REDIS(2, "redis"),
    //consul存储源
    CONSUL(3, "consul");

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
