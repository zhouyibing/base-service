package com.yipeng.baseservice.authority.result;
import com.yipeng.framework.core.constants.Direction;
import com.yipeng.framework.core.constants.annotation.FieldMapping;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.yipeng.framework.core.service.converter.*;
import lombok.Data;

/**
* 权限 返回结果
* @author yibingzhou
* email:zhouyibing_1990@163.com
* AUTO-GENERATE BY PROJECT-BUILDER
**/
@Data
@ApiModel("权限结果")
public class RightsResult {

	@ApiModelProperty("创建时间")
	@FieldMapping(name = "createTime",direction = Direction.IN, converter = StringDateConverter.class)
	private String createTime;

	@ApiModelProperty("权限代码")
	private String rightsCode;

	@ApiModelProperty("备注说明")
	private String remark;

	@ApiModelProperty("权限名")
	private String rightsName;

	@ApiModelProperty("资源地址(当rights_type为资源型时)")
	private String resourceAddress;

	@ApiModelProperty("更新时间")
	@FieldMapping(name = "updateTime",direction = Direction.IN, converter = StringDateConverter.class)
	private String updateTime;

	@ApiModelProperty("默认存活时间(-1永久有效)")
	private Integer defaultTtl;

	@ApiModelProperty("权限类型(1:资源型，2:功能型，3:数据型)")
	@FieldMapping(name = "rightsType",direction = Direction.IN, converter = IntegerByteConverter.class)
	private Integer rightsType;

	@ApiModelProperty("主键")
	private Integer id;

	@ApiModelProperty("应用id")
	private String appId;

	@ApiModelProperty("状态(0:禁用，1:启用)")
	private Integer status;

}