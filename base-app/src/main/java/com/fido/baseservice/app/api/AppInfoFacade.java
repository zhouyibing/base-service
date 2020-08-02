package com.fido.baseservice.app.api;

import com.fido.baseservice.app.result.AppInfoResult;
import com.fido.framework.core.result.Result;

/**
* 应用信息 接口
* @author yibingzhou
* email:zhouyibing_1990@163.com
* AUTO-GENERATE BY PROJECT-BUILDER
*/
public interface AppInfoFacade {

    /**
     * 获取应用信息
     * @param appId 应用id
     * @return
     */
    Result<AppInfoResult> getAppInfo(String appId);
}
