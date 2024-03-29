package com.fido.baseservice.app.controller;
import com.fido.baseservice.app.param.AppInstanceParam;
import com.fido.framework.core.result.Result;
import com.fido.framework.core.web.controller.BaseController;
import com.fido.baseservice.app.service.AppInstanceService;
import com.fido.baseservice.app.api.AppInstanceFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import com.fido.baseservice.app.result.AppInstanceResult;

/**
* @author yibingzhou
* email:zhouyibing_1990@163.com
* AUTO-GENERATE BY PROJECT-BUILDER
**/
@RestController
@RequestMapping("/appInstance")
@Api(tags = { "应用实例 接口" })
public class AppInstanceController extends BaseController<AppInstanceParam,AppInstanceResult, AppInstanceService> implements AppInstanceFacade {

    @GetMapping("/ping")
    @ApiOperation("ping连接")
    @Override
    public Result<Boolean> ping(@ApiParam(name = "appId", value = "应用id", required = true) @RequestParam("appId") String appId,
                             @ApiParam(name = "ip", value = "ip", required = true) @RequestParam("ip") String ip,
                             @ApiParam(name = "port", value = "端口", required = true, example = "123") @RequestParam("port") Integer port) {
        service.ping(appId, ip, port);
        return Result.success(true);
    }

    @GetMapping("/disconnect")
    @ApiOperation("连接断开")
    @Override
    public Result<Boolean> disconnect(@ApiParam(name = "appId", value = "应用id", required = true) @RequestParam("appId") String appId,
                       @ApiParam(name = "ip", value = "ip", required = true) @RequestParam("ip") String ip,
                       @ApiParam(name = "port", value = "端口", required = true, example = "123") @RequestParam("port") Integer port) {
        service.disconnect(appId, ip, port);
        return Result.success(true);
    }
}