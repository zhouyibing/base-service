package com.fido.baseservice.config.dao;
import com.fido.baseservice.config.mapper.ConfigMapper;
import com.fido.baseservice.config.model.db.ConfigModel;
import com.fido.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;


/**
 * @author: yibingzhou
 */
@Repository
public class ConfigDao extends BaseDao<ConfigModel, ConfigMapper> {

}
