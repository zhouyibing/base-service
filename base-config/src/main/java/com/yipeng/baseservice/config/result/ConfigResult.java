package com.yipeng.baseservice.config.result;

import com.yipeng.baseservice.config.constant.ConfigNamespace;
import com.yipeng.framework.core.constants.Direction;
import com.yipeng.framework.core.constants.annotation.FieldMapping;
import com.yipeng.framework.core.service.converter.BooleanIntegerConverter;
import com.yipeng.framework.core.service.converter.StringDateConverter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 配置项结果
 * @author: yibingzhou
 */
@Data
@ApiModel("配置项结果")
public class ConfigResult implements Serializable{

    private static final long serialVersionUID = -2060328812762157488L;
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "应用id")
    private String appId;

    @ApiModelProperty("命名空间")
    private String namespace = ConfigNamespace.DEFAULT.name();

    @ApiModelProperty("配置项名称")
    private String name;

    @ApiModelProperty("配置项值")
    private String value;

    @ApiModelProperty("描述")
    private String description;

    @FieldMapping(name = "createTime",direction = Direction.IN, converter = StringDateConverter.class)
    private String createTime;
    @FieldMapping(name = "updateTime",direction = Direction.IN, converter = StringDateConverter.class)
    private String updateTime;

    public ConfigResult() {
    }

    public ConfigResult(String appId, String namespace, String name, String value, String description) {
        this.appId = appId;
        this.namespace = namespace;
        this.name = name;
        this.value = value;
        this.description = description;
    }

    public ConfigResult(String appId, String namespace, String name, String value) {
        this.appId = appId;
        this.namespace = namespace;
        this.name = name;
        this.value = value;
    }


    public ConfigResult(String appId, String name, String value) {
        this.appId = appId;
        this.name = name;
        this.value = value;
    }
}
