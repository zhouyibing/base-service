package com.yipeng.baseservice.config.model.db;

import com.yipeng.framework.core.model.db.BaseModel;
import lombok.Data;

import javax.persistence.Table;

/**
 * @author: yibingzhou
 */
@Data
@Table(name = "b_config")
public class ConfigModel extends BaseModel<Long> {
    private static final long serialVersionUID = -5596274761738236536L;
    private String appId;
    private String namespace;
    private String name;
    private String value;
    private String description;
}
