package com.fido.baseservice.app.result;
import com.fido.framework.core.constants.Direction;
import com.fido.framework.core.constants.annotation.FieldMapping;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fido.framework.core.service.converter.*;
import lombok.Data;

/**
*  返回结果
* @author yibingzhou
* email:zhouyibing_1990@163.com
* AUTO-GENERATE BY PROJECT-BUILDER
**/
@Data
@ApiModel("应用实例结果")
public class AppInstanceResult {

	@ApiModelProperty("更新时间")
	@FieldMapping(name = "updateTime",direction = Direction.IN, converter = StringDateConverter.class)
	private String updateTime;

	@ApiModelProperty("创建时间")
	@FieldMapping(name = "createTime",direction = Direction.IN, converter = StringDateConverter.class)
	private String createTime;

	@ApiModelProperty("端口")
	private Integer port;

	@ApiModelProperty("ip")
	private String ip;

	@ApiModelProperty("主键")
	private Long id;

	@ApiModelProperty("应用id")
	private String appId;

	@ApiModelProperty("状态")
	private Integer status;

}