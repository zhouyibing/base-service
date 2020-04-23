package com.yipeng.baseservice.config.result;

import com.yipeng.baseservice.config.constant.ConfigNamespace;
import com.yipeng.framework.common.constants.Direction;
import com.yipeng.framework.common.constants.annotation.FieldMapping;
import com.yipeng.framework.common.service.converter.BooleanIntegerConverter;
import com.yipeng.framework.common.service.converter.StringDateConverter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 配置项结果
 * @author: yibingzhou
 */
@Data
public class ConfigResult implements Serializable{

    private static final long serialVersionUID = -2060328812762157488L;
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "服务id")
    private String serviceId;

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

    public ConfigResult(String serviceId, String namespace, String name, String value, String description) {
        this.serviceId = serviceId;
        this.namespace = namespace;
        this.name = name;
        this.value = value;
        this.description = description;
    }

    public ConfigResult(String serviceId, String namespace, String name, String value) {
        this.serviceId = serviceId;
        this.namespace = namespace;
        this.name = name;
        this.value = value;
    }


    public ConfigResult(String serviceId, String name, String value) {
        this.serviceId = serviceId;
        this.name = name;
        this.value = value;
    }
}
