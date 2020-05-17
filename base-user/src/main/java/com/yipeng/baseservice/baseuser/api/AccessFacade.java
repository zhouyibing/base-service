package com.yipeng.baseservice.baseuser.api;

import com.yipeng.baseservice.baseuser.param.LoginParam;
import com.yipeng.baseservice.baseuser.param.RegistryParam;
import com.yipeng.baseservice.baseuser.result.LoginResult;
import com.yipeng.baseservice.baseuser.result.RegistryResult;
import com.yipeng.framework.core.result.Result;

/**
 * 系统访问相关接口
 *
 * 登录/登出/注册/找回密码
 * @author: yibingzhou
 */
public interface AccessFacade {
    /**
     * 登录
     * @param loginParam
     * @return
     */
    Result<LoginResult> login(LoginParam loginParam);

    /**
     * 登出
     * @param loginParam
     * @return
     */
    Result<LoginResult> logOut(LoginParam loginParam);


    /**
     * 找回密码
     * @param loginParam
     * @return
     */
    Result<LoginResult> recoveredPwd(LoginParam loginParam);

    /**
     * 注册
     * @param registryParam
     * @return
     */
    Result<RegistryResult> registry(RegistryParam registryParam);
}
