package com.yipeng.baseservice.authority.param;
import com.yipeng.framework.core.model.biz.ManagedParam;
import com.yipeng.framework.core.validator.EnumValue;
import com.yipeng.framework.core.validator.Number;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.yipeng.framework.core.constants.Direction;
import com.yipeng.framework.core.constants.annotation.FieldMapping;
import com.yipeng.framework.core.service.converter.*;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
* 权限 查询参数
* @author yibingzhou
* email:zhouyibing_1990@163.com
* AUTO-GENERATE BY PROJECT-BUILDER
**/
@Data
@ApiModel("权限查询参数")
public class RightsParam extends ManagedParam<Long> {

	@ApiModelProperty("权限代码")
	private String rightsCode;

	@ApiModelProperty("备注说明")
	private String remark;

	@ApiModelProperty("权限名")
	private String rightsName;

	@ApiModelProperty("资源地址(当rights_type为资源型时)")
	private String resourceAddress;

	@ApiModelProperty("默认存活时间(-1永久有效)")
	@Range(min = -1, message = "有效期值非法")
	private Integer defaultTtl;

	@ApiModelProperty(value = "权限类型(1:资源型，2:功能型，3:数据型)", allowableValues = "1,2,3")
	@FieldMapping(name = "rightsType",direction = Direction.OUT, converter = IntegerByteConverter.class)
	@EnumValue(value = {"1", "2", "3"}, message = "权限类型仅限1/2/3")
	private Integer rightsType;

	@ApiModelProperty("应用id")
	@Number(minLen = 6, maxLen = 6, message = "应用id长度为6位数字")
	private String appId;

	@ApiModelProperty(value = "状态(0:禁用，1:启用)", allowableValues = "0,1")
	@EnumValue(value = {"0", "1"}, message = "状态仅限0/1")
	private Integer status;
}