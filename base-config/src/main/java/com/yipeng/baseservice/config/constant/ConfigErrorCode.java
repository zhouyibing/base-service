package com.yipeng.baseservice.config.constant;

import com.yipeng.framework.core.exception.ErrorCode;

/**
 * @author: yibingzhou
 */
public interface ConfigErrorCode {
    ErrorCode CONFIG_EXISTED = new ErrorCode("2001", "配置已存在");
    ErrorCode APP_NOT_REGISTERED = new ErrorCode("2003", "服务[{}-{}]未注册,请联系管理员注册");
}
