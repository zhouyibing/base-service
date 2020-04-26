package com.yipeng.baseservice.baseuser.service.login;

import com.yipeng.baseservice.baseuser.param.LoginParam;

/**
 * @author: yibingzhou
 */
public interface LoginService {

    /**
     * 登录
     * @param loginParam 登录参数
     */
    void login(LoginParam loginParam);
}
