package com.fido.baseservice.config.service;
import com.fido.baseservice.config.dao.ConfigDao;
import com.fido.baseservice.config.model.db.ConfigModel;
import com.fido.baseservice.config.param.ConfigParam;
import com.fido.framework.core.exception.ExceptionUtil;
import com.fido.framework.core.model.db.AccessObject;
import com.fido.framework.core.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * @author: yibingzhou
 */
@Service
public class ConfigService extends BaseService<ConfigModel, ConfigDao> {
    @Override
    public boolean createIfAbsent(AccessObject query, AccessObject create) throws ExceptionUtil.BizException {
        ConfigParam simple = new ConfigParam();
        simple.setAppId(((ConfigParam)query).getAppId());
        simple.setName(((ConfigParam)query).getName());
        simple.setNamespace(((ConfigParam)query).getNamespace());
        return super.createIfAbsent(simple, create);
    }
}
