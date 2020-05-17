package com.yipeng.baseservice.authority.result;
import com.yipeng.framework.core.constants.Direction;
import com.yipeng.framework.core.constants.annotation.FieldMapping;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.yipeng.framework.core.service.converter.*;
import lombok.Data;

/**
* 角色 返回结果
* @author yibingzhou
* email:zhouyibing_1990@163.com
* AUTO-GENERATE BY PROJECT-BUILDER
**/
@Data
@ApiModel("角色结果")
public class RoleResult {

	@ApiModelProperty("角色名称")
	private String roleName;

	@ApiModelProperty("更新时间")
	@FieldMapping(name = "updateTime",direction = Direction.IN, converter = StringDateConverter.class)
	private String updateTime;

	@ApiModelProperty("创建时间")
	@FieldMapping(name = "createTime",direction = Direction.IN, converter = StringDateConverter.class)
	private String createTime;

	@ApiModelProperty("默认存活时间(-1永久有效)")
	private Integer defaultTtl;

	@ApiModelProperty("角色代码")
	private String roleCode;

	@ApiModelProperty("备注说明")
	private String remark;

	@ApiModelProperty("主键")
	private Integer id;

	@ApiModelProperty("应用id")
	private String appId;

	@ApiModelProperty("状态(0:禁用，1:启用)")
	private Integer status;

}