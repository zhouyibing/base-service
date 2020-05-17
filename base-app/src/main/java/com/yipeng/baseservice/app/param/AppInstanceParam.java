package com.yipeng.baseservice.app.param;
import com.yipeng.framework.core.model.biz.BaseParam;
import com.yipeng.framework.core.validator.Number;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.yipeng.framework.core.constants.Direction;
import com.yipeng.framework.core.constants.annotation.FieldMapping;
import com.yipeng.framework.core.service.converter.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import java.util.Date;
/**
*  查询参数
* @author yibingzhou
* email:zhouyibing_1990@163.com
* AUTO-GENERATE BY PROJECT-BUILDER
**/
@ApiModel("应用实例查询参数")
@Data
public class AppInstanceParam extends BaseParam {

	@ApiModelProperty("ip")
	@Length(max = 15, message = "ip地址长度不能超过15位")
	private String ip;

	@ApiModelProperty("端口")
	@Range(min = 0, max = 65535, message = "端口限制0~65535")
	private Integer port;

	@ApiModelProperty("应用id")
	@NotBlank(message = "应用id不能为空")
	@Number(minLen = 6,maxLen = 6,message = "应用id必须为6位数字")
	private String appId;

	@ApiModelProperty("状态")
	private Integer status;
}