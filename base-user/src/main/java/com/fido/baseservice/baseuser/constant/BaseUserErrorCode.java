package com.fido.baseservice.baseuser.constant;


import com.fido.framework.core.exception.ErrorCode;

/**
 * base-user错误代码
 * @author: yibingzhou
 */
public interface BaseUserErrorCode {
    ErrorCode UNSUPPORTTED_TYPE = new ErrorCode("0101000", "不支持的类型");
}
