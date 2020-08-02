package com.fido.baseservice.config.param;
import com.fido.framework.core.model.biz.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 配置项参数
 * serviceId,namespace,name全部转小写
 * @author: yibingzhou
 */
@Data
@ApiModel("配置项参数")
public class ConfigParam extends BaseParam<Long> {
    @ApiModelProperty(value = "服务id", required = true)
    @NotBlank(message = "应用id不能为空")
    private String appId;

    @ApiModelProperty("命名空间")
    private String namespace;

    @ApiModelProperty("配置项存储源")
    private String source;

    @ApiModelProperty(value = "配置项名称")
    private String name;

    @ApiModelProperty(value = "配置项值")
    private String value;

    @ApiModelProperty("描述")
    private String description;

    public ConfigParam() {
    }

    public ConfigParam(String appId, String namespace, String name) {
        this.appId = appId;
        this.namespace = namespace;
        this.name = name;
    }

    public ConfigParam(String appId, String namespace, String name, String value) {
        this.appId = appId;
        this.namespace = namespace;
        this.name = name;
        this.value = value;
    }
}
