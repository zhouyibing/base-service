package com.yipeng.baseservice.config.constant;

import com.yipeng.framework.core.exception.ErrorCode;
import com.yipeng.framework.core.utils.ConfigUtils;

/**
 * @author: yibingzhou
 */
public class ConfigErrorCode {
    private static final String APP_ID = ConfigUtils.getProperty("dev-framework.appInfo.appId");
    public static final ErrorCode CONFIG_EXISTED = new ErrorCode(APP_ID+"2001", "配置已存在");
    public static final ErrorCode APP_NOT_REGISTERED = new ErrorCode(APP_ID+"2003", "服务[{}-{}]未注册,请联系管理员注册");
}
