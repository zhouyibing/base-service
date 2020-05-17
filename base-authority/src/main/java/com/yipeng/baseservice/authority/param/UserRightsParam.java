package com.yipeng.baseservice.authority.param;

import com.yipeng.framework.core.model.biz.ManagedParam;
import com.yipeng.framework.core.validator.EnumValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 * 用户特殊权限
 * @author: yibingzhou
 */
@Data
@ApiModel("用户特殊权限")
public class UserRightsParam extends ManagedParam {

    @ApiModelProperty("用户id")
    @NotBlank(message = "用户id不能为空")
    private Long userId;

    @ApiModelProperty("权限id")
    @NotBlank(message = "权限id不能为空")
    private Long rightsId;

    @ApiModelProperty("有效期")
    @Range(min = -1, message = "有效期值非法")
    private Long ttl;

    @ApiModelProperty("状态(0禁用，1启用，2临时启用)")
    @EnumValue(value = {"0", "1", "2"}, message = "状态仅限0/1/2")
    private Integer status;
}
