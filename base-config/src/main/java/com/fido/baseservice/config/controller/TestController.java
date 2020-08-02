package com.fido.baseservice.config.controller;

import com.fido.baseservice.config.model.db.ConfigModel;
import com.fido.baseservice.config.param.ConfigParam;
import com.fido.baseservice.config.service.ConfigService;
import com.fido.framework.core.web.controller.BaseController;
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
