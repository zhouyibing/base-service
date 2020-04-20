package com.yipeng.baseservice.config.controller;

import cn.hutool.core.lang.Func;
import com.yipeng.baseservice.config.param.ConfigParam;
import com.yipeng.baseservice.config.result.ConfigResult;
import com.yipeng.baseservice.config.service.ConfigService;
import com.yipeng.framework.common.web.controller.BaseController;
import com.yipeng.framework.common.model.Intensifier;
import com.yipeng.framework.common.utils.Precondition;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.PostConstruct;
import java.util.function.Function;

/**
 * @author: yibingzhou
 */
@RestController
@RequestMapping("/base-config")
@Api(tags = { "配置管理接口" })
public class ConfigController extends BaseController<ConfigParam,ConfigService>{

    @PostConstruct
    public void init() {
        Function<ConfigParam,ConfigParam> argumentChecker = (param) -> {
            Precondition.checkNotBlank(param.getValue(), "配置值不能为空");
            Precondition.checkNotBlank(param.getNamespace(), "命名空间");
            Precondition.checkNotBlank(param.getName(), "配置name不能为空");
            return param;
        };
        Function<ConfigParam,ConfigParam> argumentToLowCase = (configParam) -> {
            if(!(configParam instanceof ConfigParam)) return configParam;
            //转小写
            if(StringUtils.isNotBlank(configParam.getServiceId())) {
                configParam.setServiceId(configParam.getServiceId().toLowerCase());
            }
            if(StringUtils.isNotBlank(configParam.getNamespace())) {
                configParam.setNamespace(configParam.getNamespace().toLowerCase());
            }
            if(StringUtils.isNotBlank(configParam.getName())) {
                configParam.setName(configParam.getName().toLowerCase());
            }
            return configParam;
        };
        addIntensifier(new Intensifier("save").before(argumentChecker));
        addIntensifier(new Intensifier("creatIfAbsent").before(argumentChecker));
        addIntensifier(new Intensifier(ALL).before(argumentToLowCase).priority(Integer.MAX_VALUE).useBeforeEnhanceResult(true));
    }

    @Override
    protected Class defaultResultClass() {
        return ConfigResult.class;
    }
}
