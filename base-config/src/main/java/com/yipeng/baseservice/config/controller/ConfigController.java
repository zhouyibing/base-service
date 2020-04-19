package com.yipeng.baseservice.config.controller;

import com.yipeng.baseservice.config.param.ConfigParam;
import com.yipeng.baseservice.config.service.ConfigService;
import com.yipeng.framework.common.web.controller.BaseController;
import com.yipeng.framework.common.model.Intensifier;
import com.yipeng.framework.common.utils.Precondition;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.PostConstruct;

/**
 * @author: yibingzhou
 */
@RestController
@RequestMapping("/base-config")
@Api(tags = { "配置管理接口" })
public class ConfigController extends BaseController<ConfigParam,ConfigService>{

    @PostConstruct
    public void init() {
        addIntensifier(new Intensifier("save").before((param) -> {
            Precondition.checkNotBlank(((ConfigParam)param).getValue(), "配置值不能为空");
            Precondition.checkNotBlank(((ConfigParam)param).getName(), "配置name不能为空");
            return param;
        }));
        addIntensifier(new Intensifier(ALL).before((param) -> {
            //转小写
            ConfigParam configParam = (ConfigParam) param;
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
        }).priority(Integer.MAX_VALUE).useBeforeEnhanceResult(true));
    }
}
