package com.yipeng.baseservice.config.model.db;

import com.yipeng.baseservice.config.constant.ConfigNamespace;
import com.yipeng.framework.common.constants.annotation.Convert;
import com.yipeng.framework.common.model.BaseModel;
import lombok.Data;

import javax.persistence.Table;

/**
 * @author: yibingzhou
 */
@Data
@Table(name = "b_config")
@Convert(ignores = {"logicDelete","updaterId","creatorId"})
public class ConfigModel extends BaseModel {
    private static final long serialVersionUID = -5596274761738236536L;
    private String serviceId;
    private String namespace = ConfigNamespace.DEFAULT.name();
    private String name;
    private String value;
    private String description;
}
