package com.yipeng.baseservice.authority.param;
import com.yipeng.framework.core.model.biz.ManagedParam;
import com.yipeng.framework.core.validator.EnumValue;
import com.yipeng.framework.core.validator.Number;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 角色 查询参数
* @author yibingzhou
* email:zhouyibing_1990@163.com
* AUTO-GENERATE BY PROJECT-BUILDER
**/
@Data
@ApiModel("角色查询参数")
public class RoleParam extends ManagedParam {


	@ApiModelProperty("角色名称")
	private String roleName;

	@ApiModelProperty("默认存活时间(-1永久有效)")
	private Integer defaultTtl;

	@ApiModelProperty("角色代码")
	private String roleCode;

	@ApiModelProperty("备注说明")
	private String remark;

	@ApiModelProperty("应用id")
	@Number(minLen = 6, maxLen = 6, message = "应用id长度为6位数字")
	private String appId;

	@ApiModelProperty("状态(0:禁用，1:启用)")
	@EnumValue(value = {"0", "1"}, message = "状态仅限0/1")
	private Integer status;
}