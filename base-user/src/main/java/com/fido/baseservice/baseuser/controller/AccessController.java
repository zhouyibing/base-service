package com.fido.baseservice.baseuser.controller;

import com.fido.baseservice.baseuser.api.AccessFacade;
import com.fido.baseservice.baseuser.param.LoginParam;
import com.fido.baseservice.baseuser.param.RegistryParam;
import com.fido.baseservice.baseuser.result.LoginResult;
import com.fido.baseservice.baseuser.result.RegistryResult;
import com.fido.framework.core.result.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统访问接口
 * @author: yibingzhou
 */
@RestController
public class AccessController implements AccessFacade {

    @Override
    @PostMapping("/api/base-user/access/login")
    public Result<LoginResult> login(LoginParam loginParam) {
        return null;
    }

    @Override
    @PostMapping("/api/base-user/access/logOut")
    public Result<LoginResult> logOut(LoginParam loginParam) {
        return null;
    }

    @Override
    @PostMapping("/api/base-user/access/recoveredPwd")
    public Result<LoginResult> recoveredPwd(LoginParam loginParam) {
        return null;
    }

    @Override
    @PostMapping("/api/base-user/access/registry")
    public Result<RegistryResult> registry(RegistryParam registryParam) {
        return null;
    }


}
