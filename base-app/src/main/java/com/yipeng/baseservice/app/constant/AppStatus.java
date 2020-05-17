package com.yipeng.baseservice.app.constant;

import com.yipeng.framework.core.constants.CodeDesEnum;
import lombok.Getter;

/**
 * 应用状态
 * @author: yibingzhou
 */
public enum AppStatus implements CodeDesEnum<Integer, AppStatus> {
    //正常，未启动
    NORMAL(0, "正常"),
    //正在运行
    RUNNING(1, "运行中"),
    //停止运行
    STOP(-1, "停止"),
    //已废弃
    DISCARD(-2, "已废弃");

    @Getter
    private Integer code;

    @Getter
    private String description;

    AppStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }


    @Override
    public AppStatus[] all() {
        return AppStatus.values();
    }
}
