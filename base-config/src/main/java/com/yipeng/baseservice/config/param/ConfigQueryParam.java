package com.yipeng.baseservice.config.param;

import com.yipeng.framework.common.model.AccessObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author: yibingzhou
 */
@Data
public class ConfigQueryParam extends AccessObject {

    @NotBlank(message = "服务id不能为空")
    @ApiModelProperty(value = "服务id", required = true)
    private String serviceId;

    @ApiModelProperty("命名空间")
    private String namespace;

    @ApiModelProperty("配置项存储源")
    private String source;

    @ApiModelProperty("配置项名称")
    private String name;
}
