package com.yipeng.baseservice.authority.controller;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.yipeng.baseservice.authority.param.RoleParam;
import com.yipeng.baseservice.authority.param.RoleRightsParam;
import com.yipeng.baseservice.authority.param.UserRoleParam;
import com.yipeng.framework.core.result.Result;
import com.yipeng.framework.core.utils.Precondition;
import com.yipeng.framework.core.web.controller.BaseController;
import com.yipeng.baseservice.authority.service.RoleService;
import com.yipeng.baseservice.authority.api.RoleFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import com.yipeng.baseservice.authority.result.RoleResult;

import javax.validation.Valid;

/**
* @author yibingzhou
* email:zhouyibing_1990@163.com
* AUTO-GENERATE BY PROJECT-BUILDER
**/
@RestController
@RequestMapping("/role")
@Api(tags = { "角色 接口" })
public class RoleController extends BaseController<RoleParam,RoleResult, RoleService> implements RoleFacade {

    @PostMapping("/empowerment")
    @ApiOperation("角色赋权")
    public Result<Boolean> empowerment(@ApiParam(name = "roleRightsParam", value = "角色赋权参数") @RequestBody @Valid RoleRightsParam roleRightsParam) {
        checkManagedParam(roleRightsParam);
        service.empowerment(roleRightsParam);
        return Result.success(true);
    }

    @PostMapping("/disfranchise")
    @ApiOperation("权限收回")
    public Result<Boolean> disfranchise(@ApiParam(name = "roleRightsParam", value = "角色权限参数") @RequestBody @Valid RoleRightsParam roleRightsParam) {
        checkUpdater(roleRightsParam);
        service.disfranchise(roleRightsParam);
        return Result.success(true);
    }

    @PostMapping("/enableRights")
    @ApiOperation("权限启用")
    public Result<Boolean> enableRights(@ApiParam(name = "roleRightsParam", value = "角色权限参数") @RequestBody @Valid RoleRightsParam roleRightsParam) {
        checkUpdater(roleRightsParam);
        service.enableRights(roleRightsParam);
        return Result.success(true);
    }

    @PostMapping("/grantRole")
    @ApiOperation("授予用户角色")
    public Result<Boolean> grantRole(@ApiParam(name = "userRoleParam", value = "用户角色参数") @RequestBody @Valid UserRoleParam userRoleParam) {
        checkManagedParam(userRoleParam);
        service.grantRole(userRoleParam);
        return Result.success(true);
    }

    @PostMapping("/revokeRole")
    @ApiOperation("回收用户角色")
    public Result<Boolean> revokeRole(@ApiParam(name = "userRoleParam", value = "用户角色参数") @RequestBody @Valid UserRoleParam userRoleParam) {
        checkUpdater(userRoleParam);
        service.revokeRole(userRoleParam);
        return Result.success(true);
    }
}