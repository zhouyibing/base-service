package com.yipeng.baseservice.config.param;
import com.yipeng.framework.common.constants.Direction;
import com.yipeng.framework.common.constants.annotation.FieldMapping;
import com.yipeng.framework.common.model.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 配置项参数
 * serviceId,namespace,name全部转小写
 * @author: yibingzhou
 */
@Data
public class ConfigParam extends BaseParam {
    @ApiModelProperty(value = "服务id", required = true)
    @NotBlank(message = "服务id不能为空")
    private String serviceId;

    @ApiModelProperty("命名空间")
    private String namespace;

    @ApiModelProperty("配置项存储源")
    private String source;

    @ApiModelProperty(value = "配置项名称", required = true)
    private String name;

    @ApiModelProperty(value = "配置项值", required = true)
    private String value;

    @ApiModelProperty("描述")
    @FieldMapping(name = "description", direction = Direction.OUT)
    private String desc;

    public ConfigParam() {
    }

    public ConfigParam(String serviceId, String namespace, String name) {
        this.serviceId = serviceId;
        this.namespace = namespace;
        this.name = name;
    }

    public ConfigParam(String serviceId, String namespace, String name, String value) {
        this.serviceId = serviceId;
        this.namespace = namespace;
        this.name = name;
        this.value = value;
    }
}
