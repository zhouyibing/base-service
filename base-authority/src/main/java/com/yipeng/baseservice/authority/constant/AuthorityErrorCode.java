package com.yipeng.baseservice.authority.constant;

import com.yipeng.framework.core.exception.ErrorCode;

/**
* Authority 错误代码定义
* @author yibingzhou
* email:zhouyibing_1990@163.com
* AUTO-GENERATE BY PROJECT-BUILDER
*/
public interface AuthorityErrorCode {
   /**
   * Example:
   *public final static ErrorCode SERVER_INTERNAL_ERROR = new ErrorCode("0000", "服务器内部错误:{0#60}");
   *public final static ErrorCode UNSUPPORTTED_TYPE = new ErrorCode("0002", "不支持的类型");
   *public final static ErrorCode ILLEGAL_ARGUMENT = new ErrorCode("0004", "参数错误");
   *public final static ErrorCode CAN_NOT_NULL = new ErrorCode("0006", "参数不能为空");
   **/

   ErrorCode ROLE_NOT_BELONG = new ErrorCode("3001", "角色:{0}不属于应用:{1}");
   ErrorCode NOT_FOUND_RIGHTS = new ErrorCode("3003", "未找到权限:{0}");
   ErrorCode RIGHTS_NOT_BELONG = new ErrorCode("3005", "权限:{0}不属于应用:{1}");
}
