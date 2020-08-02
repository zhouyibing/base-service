package com.fido.baseservice.app.controller;
import com.fido.baseservice.app.param.AppInfoParam;
import com.fido.framework.core.result.Result;
import com.fido.framework.core.utils.Precondition;
import com.fido.framework.core.web.controller.BaseController;
import com.fido.baseservice.app.service.AppInfoService;
import com.fido.baseservice.app.api.AppInfoFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.fido.baseservice.app.result.AppInfoResult;

/**
* @author yibingzhou
* email:zhouyibing_1990@163.com
* AUTO-GENERATE BY PROJECT-BUILDER
**/
@RestController
@RequestMapping("/appInfo")
@Api(tags = { "应用信息 接口" })
public class AppInfoController extends BaseController<AppInfoParam,AppInfoResult, AppInfoService> implements AppInfoFacade {

    @GetMapping("/getAppInfo")
    @ApiOperation("获得应用信息")
    @Override
    public Result<AppInfoResult> getAppInfo(@ApiParam(name = "appId", value = "应用id", required = true) @RequestParam(value = "appId") String appId) {
        return Result.success(service.getAppInfo(appId));
    }

    @PostMapping("/registry")
    @ApiOperation("应用注册")
    public Result<Boolean> registry(@ApiParam(name = "appInfo", value = "应用注册信息", required = true) @RequestBody AppInfoParam appInfoModel) {
        Precondition.checkNotBlank(appInfoModel.getSystemId(), "系统id不能为空");
        Precondition.checkNotBlank(appInfoModel.getServiceId(), "服务id不能为空");
        Precondition.checkNotBlank(appInfoModel.getManager(), "请设置负责人");
        Precondition.checkArgument(appInfoModel.getManagerId() != null, "负责人id不能为空");
        Precondition.checkArgument(!StringUtils.isEmpty(appInfoModel.getManagerMobile())
                || !StringUtils.isEmpty(appInfoModel.getManagerEmail()) , "请设置负责人手机或邮箱");
        Precondition.checkNotBlank(appInfoModel.getCandidate(), "请设置候选人");
        Precondition.checkArgument(appInfoModel.getCandidateId() != null, "候选人id不能为空");
        Precondition.checkArgument(!appInfoModel.getCandidateId().equals(appInfoModel.getManagerId()), "负责人与候选人不能是同一人");
        Precondition.checkArgument(!StringUtils.isEmpty(appInfoModel.getCandidateMobile())
                || !StringUtils.isEmpty(appInfoModel.getCandidateEmail()), "请设置候选人手机或邮箱");
        checkManagedParam(appInfoModel);
        return Result.success(service.createIfAbsent(appInfoModel, appInfoModel));
    }
}