package com.yipeng.baseservice.config.service;
import com.yipeng.baseservice.config.dao.ConfigDao;
import com.yipeng.baseservice.config.model.db.ConfigModel;
import com.yipeng.baseservice.config.result.ConfigResult;
import com.yipeng.framework.common.service.BaseService;
import io.swagger.annotations.ApiModel;
import org.springframework.stereotype.Service;


/**
 * @author: yibingzhou
 */
@Service
@ApiModel()
public class ConfigService extends BaseService<ConfigResult, ConfigModel, ConfigDao> {
}
