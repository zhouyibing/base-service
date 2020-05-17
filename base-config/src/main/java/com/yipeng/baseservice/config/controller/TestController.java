package com.yipeng.baseservice.config.controller;

import com.yipeng.baseservice.config.model.db.ConfigModel;
import com.yipeng.baseservice.config.param.ConfigParam;
import com.yipeng.baseservice.config.result.ConfigResult;
import com.yipeng.baseservice.config.service.ConfigService;
import com.yipeng.framework.core.web.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yibingzhou
 */
@RestController
@RequestMapping("/test")
@Api(tags = { "测试接口" })
public class TestController extends BaseController<ConfigParam,ConfigModel,ConfigService>{
}
