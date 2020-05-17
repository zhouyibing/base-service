package com.yipeng.baseservice.config.constant;

import com.yipeng.framework.core.exception.ErrorCode;

/**
 * @author: yibingzhou
 */
public interface ConfigErrorCode {
    ErrorCode CONFIG_EXISTED = new ErrorCode("0007", "配置已存在");
    ErrorCode APP_NOT_REGISTERED = new ErrorCode("0009", "服务[{}-{}]未注册,请联系管理员注册");
}
