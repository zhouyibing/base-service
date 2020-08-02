package com.fido.baseservice.config.param;

import com.fido.framework.core.model.db.AccessObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author: yibingzhou
 */
@Data
@ApiModel("配置查询参数")
public class ConfigQueryParam extends AccessObject {

    @NotBlank(message = "应用id不能为空")
    @ApiModelProperty(value = "应用id", required = true)
    private String appId;

    @ApiModelProperty("命名空间")
    private String namespace;

    @ApiModelProperty("配置项存储源")
    private String source;

    @ApiModelProperty("配置项名称")
    private String name;
}
