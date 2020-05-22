package com.yipeng.baseservice.authority.controller;
import com.yipeng.baseservice.authority.param.RightsParam;
import com.yipeng.baseservice.authority.param.UserRightsParam;
import com.yipeng.baseservice.authority.param.UserRoleParam;
import com.yipeng.framework.core.result.Result;
import com.yipeng.framework.core.web.controller.BaseController;
import com.yipeng.baseservice.authority.service.RightsService;
import com.yipeng.baseservice.authority.api.RightsFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import com.yipeng.baseservice.authority.result.RightsResult;

import javax.validation.Valid;
import java.util.List;

/**
* @author yibingzhou
* email:zhouyibing_1990@163.com
* AUTO-GENERATE BY PROJECT-BUILDER
**/
@RestController
@RequestMapping("/rights")
@Api(tags = { "权限 接口" })
public class RightsController extends BaseController<RightsParam,RightsResult, RightsService> implements RightsFacade {

    @PostMapping("/disfranchise")
    @ApiOperation("权限收回")
    public Result<Boolean> disfranchise(@ApiParam(name = "appId", value = "应用id", required = true) @RequestParam String appId,
                               @ApiParam(name = "updaterId", value = "更新者id", required = true) @RequestParam String updaterId,
                               @ApiParam(name = "rightsIdList", value = "权限id列表", required = true) @RequestBody List<Long> rightsIdList) {
        service.disfranchise(appId, updaterId, rightsIdList);
        return Result.success(true);
    }

    @PostMapping("/enableRights")
    @ApiOperation("权限启用")
    public Result<Boolean> enableRights(@ApiParam(name = "appId", value = "应用id", required = true) @RequestParam String appId,
                               @ApiParam(name = "updaterId", value = "更新者id", required = true) @RequestParam String updaterId,
                               @ApiParam(name = "rightsIdList", value = "权限id列表", required = true) @RequestBody List<Long> rightsIdList) {
        service.enableRights(appId, updaterId, rightsIdList);
        return Result.success(true);
    }

    @PostMapping("/grantUserRights")
    @ApiOperation("授予用户特殊权限")
    public Result<Boolean> grantUserRights(@ApiParam(name = "userRightsParam", value = "用户权限参数") @RequestBody @Valid UserRightsParam userRightsParam) {
        checkUpdater(userRightsParam);
        service.grantUserRights(userRightsParam);
        return Result.success(true);
    }

    @PostMapping("/revokeUserRights")
    @ApiOperation("回收用户特殊权限")
    public Result<Boolean> revokeUserRights(@ApiParam(name = "userRightsParam", value = "用户权限参数") @RequestBody @Valid UserRightsParam userRightsParam) {
        checkUpdater(userRightsParam);
        service.revokeUserRights(userRightsParam);
        return Result.success(true);
    }
}