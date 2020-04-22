package com.yipeng.baseservice.config.service;
import com.yipeng.baseservice.config.dao.ConfigDao;
import com.yipeng.baseservice.config.model.db.ConfigModel;
import com.yipeng.baseservice.config.param.ConfigParam;
import com.yipeng.framework.common.exception.ExceptionUtil;
import com.yipeng.framework.common.model.AccessObject;
import com.yipeng.framework.common.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * @author: yibingzhou
 */
@Service
public class ConfigService extends BaseService<ConfigModel, ConfigDao> {
    @Override
    public boolean createIfAbsent(AccessObject query, AccessObject create) throws ExceptionUtil.BizException {
        ConfigParam simple = new ConfigParam();
        simple.setServiceId(((ConfigParam)query).getServiceId());
        simple.setName(((ConfigParam)query).getName());
        simple.setNamespace(((ConfigParam)query).getNamespace());
        return super.createIfAbsent(simple, create);
    }
}
