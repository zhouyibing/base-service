package com.yipeng.baseservice.baseuser.controller;

import com.yipeng.baseservice.baseuser.api.AccessFacade;
import com.yipeng.baseservice.baseuser.param.LoginParam;
import com.yipeng.baseservice.baseuser.param.RegistryParam;
import com.yipeng.baseservice.baseuser.result.LoginResult;
import com.yipeng.baseservice.baseuser.result.RegistryResult;
import com.yipeng.framework.common.result.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统访问接口
 * @author: yibingzhou
 */
@RestController
public class AccessController implements AccessFacade {

    @PostMapping("/api/base-user/access/login")
    public Result<LoginResult> login(LoginParam loginParam) {
        return null;
    }

    @PostMapping("/api/base-user/access/logOut")
    public Result<LoginResult> logOut(LoginParam loginParam) {
        return null;
    }

    @PostMapping("/api/base-user/access/recoveredPwd")
    public Result<LoginResult> recoveredPwd(LoginParam loginParam) {
        return null;
    }

    @PostMapping("/api/base-user/access/registry")
    public Result<RegistryResult> registry(RegistryParam registryParam) {
        return null;
    }


}
