package com.yipeng.baseservice.authority.param;

import com.yipeng.framework.core.model.biz.ManagedParam;
import com.yipeng.framework.core.validator.EnumValue;
import com.yipeng.framework.core.validator.Number;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import java.util.List;

/**
 * 角色-权限参数
 * @author: yibingzhou
 */
@Data
@ApiModel("角色-权限参数")
public class RoleRightsParam extends ManagedParam {

    @ApiModelProperty("应用id")
    @NotBlank(message = "应用id不能为空")
    @Number(minLen = 6, maxLen = 6, message = "应用id长度为6位数字")
    private String appId;

    @ApiModelProperty("角色id")
    @NotBlank(message = "角色id不能为空")
    private Long roleId;

    @ApiModelProperty("权限列表")
    @Valid
    private List<RightsItem> rights;

    @Data
    @ApiModel("权限项参数")
    public class RightsItem{
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
}
