package com.yipeng.baseservice.authority.controller;

import com.yipeng.baseservice.authority.api.UserPermissionFacade;
import com.yipeng.baseservice.authority.result.UserPermissions;
import com.yipeng.baseservice.authority.service.UserPermissionService;
import com.yipeng.framework.core.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户权限接口
 * @author: yibingzhou
 */
@RestController
@RequestMapping("/userPermission")
@Api(tags = { "用户权限 接口" })
public class UserPermissionController implements UserPermissionFacade {

    @Autowired
    private UserPermissionService userPermissionService;


    @PostMapping("/query")
    @ApiOperation("查询用户权限列表")
    public Result<UserPermissions> queryUserPermissions(@ApiParam(name = "appId", value = "应用id", required = true) @RequestParam String appId, @ApiParam(name = "userId", value = "用户id", required = true) @RequestParam Long userId) {
        return Result.success(userPermissionService.queryUserPermissions(appId, userId));
    }
}
