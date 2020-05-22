package com.yipeng.baseservice.app.constant;

import com.yipeng.framework.core.exception.ErrorCode;
import com.yipeng.framework.core.utils.ConfigUtils;

/**
 * @author: yibingzhou
 */
public class AppInfoErrorCode {
    private static final String APP_ID = ConfigUtils.getProperty("dev-framework.appInfo.appId");
    public static final ErrorCode APP_UNREGISTERED = new ErrorCode(APP_ID+"1001", "应用未注册");

}
