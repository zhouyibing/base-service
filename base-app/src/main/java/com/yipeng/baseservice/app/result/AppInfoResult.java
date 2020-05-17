package com.yipeng.baseservice.app.result;
import com.yipeng.framework.core.constants.Direction;
import com.yipeng.framework.core.constants.annotation.FieldMapping;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.yipeng.framework.core.service.converter.*;
import lombok.Data;

import javax.persistence.Column;

/**
* 应用信息 返回结果
* @author yibingzhou
* email:zhouyibing_1990@163.com
* AUTO-GENERATE BY PROJECT-BUILDER
**/
@Data
@ApiModel("应用信息结果")
public class AppInfoResult {

	@ApiModelProperty("创建时间")
	@FieldMapping(name = "createTime",direction = Direction.IN, converter = StringDateConverter.class)
	private String createTime;

	@ApiModelProperty("系统id")
	private String systemId;

	@ApiModelProperty("服务名称")
	private String serviceName;

	@ApiModelProperty("系统名称")
	private String systemName;

	@ApiModelProperty("描述")
	private String description;

	@ApiModelProperty("接口文档地址")
	private String docUrl;

	@ApiModelProperty("更新时间")
	@FieldMapping(name = "updateTime",direction = Direction.IN, converter = StringDateConverter.class)
	private String updateTime;

	@ApiModelProperty("服务id")
	private String serviceId;

	@ApiModelProperty("创建者")
	private String creatorId;

	@ApiModelProperty("更新者")
	private String updaterId;

	@ApiModelProperty("主键")
	private Integer id;

	@ApiModelProperty("应用id(system_id，service_id组合)")
	private String appId;

	@ApiModelProperty("状态")
	private Integer status;

	@ApiModelProperty("负责人")
	private String manager;

	@ApiModelProperty("负责人id")
	private Long managerId;

	@ApiModelProperty("负责人手机")
	private String managerMobile;

	@ApiModelProperty("负责人email")
	private String managerEmail;

	@ApiModelProperty("候选人")
	private String candidate;

	@ApiModelProperty("候选人id")
	private Long candidateId;

	@ApiModelProperty("候选人手机")
	private String candidateMobile;

	@ApiModelProperty("候选人email")
	private String candidateEmail;

}