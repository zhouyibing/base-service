package com.yipeng.baseservice.config.dao;
import com.yipeng.baseservice.config.mapper.ConfigMapper;
import com.yipeng.baseservice.config.model.db.ConfigModel;
import com.yipeng.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;


/**
 * @author: yibingzhou
 */
@Repository
public class ConfigDao extends BaseDao<ConfigModel, ConfigMapper> {

}
