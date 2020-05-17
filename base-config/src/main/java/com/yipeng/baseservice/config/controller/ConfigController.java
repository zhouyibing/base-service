package com.yipeng.baseservice.config.controller;

import com.yipeng.baseservice.config.api.ConfigFacade;
import com.yipeng.baseservice.config.param.ConfigParam;
import com.yipeng.baseservice.config.result.ConfigResult;
import com.yipeng.baseservice.config.service.ConfigService;
import com.yipeng.framework.core.web.controller.BaseController;
import com.yipeng.framework.core.model.biz.Intensifier;
import com.yipeng.framework.core.utils.Precondition;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.PostConstruct;
import java.util.function.Function;

/**
 * @author: yibingzhou
 */
@RestController
@RequestMapping("/config")
@Api(tags = { "配置管理接口" })
public class ConfigController extends BaseController<ConfigParam,ConfigResult,ConfigService> implements ConfigFacade {

    @PostConstruct
    public void init() {
        Function<?,?> argumentChecker = (param) -> {
            if(!(param instanceof ConfigParam)) {
                return param;
            }
            Precondition.checkNotBlank(((ConfigParam)param).getValue(), "配置值不能为空");
            Precondition.checkNotBlank(((ConfigParam)param).getNamespace(), "命名空间");
            Precondition.checkNotBlank(((ConfigParam)param).getName(), "配置name不能为空");
            return param;
        };
        Function<?,?> argumentToLowCase = (param) -> {
            if(!(param instanceof ConfigParam)) {
                return param;
            }
            //转小写
            if(StringUtils.isNotBlank(((ConfigParam)param).getAppId())) {
                ((ConfigParam)param).setAppId(((ConfigParam)param).getAppId().toLowerCase());
            }
            if(StringUtils.isNotBlank(((ConfigParam)param).getNamespace())) {
                ((ConfigParam)param).setNamespace(((ConfigParam)param).getNamespace().toLowerCase());
            }
            if(StringUtils.isNotBlank(((ConfigParam)param).getName())) {
                ((ConfigParam)param).setName(((ConfigParam)param).getName().toLowerCase());
            }
            return param;
        };
        addIntensifier(new Intensifier("save").before(argumentChecker));
        addIntensifier(new Intensifier("creatIfAbsent").before(argumentChecker));
        addIntensifier(new Intensifier(ALL).before(argumentToLowCase).priority(Integer.MAX_VALUE).useBeforeEnhanceResult(true));
    }
}
