package com.fido.baseservice.app.api;

import com.fido.framework.core.result.Result;

/**
* 应用实例 接口
* @author yibingzhou
* email:zhouyibing_1990@163.com
* AUTO-GENERATE BY PROJECT-BUILDER
*/
public interface AppInstanceFacade {

    /**
     * 应用实例ping连接
     * @return
     */
    Result ping(String appId, String ip, Integer port);

    /**
     * 连接断开
     * @param appId 应用id
     * @param ip ip
     * @param port 断开
     * @return
     */
    Result disconnect(String appId, String ip, Integer port);
}
