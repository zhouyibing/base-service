package com.fido.baseservice.app.param;
import com.fido.framework.core.model.biz.ManagedParam;
import com.fido.framework.core.validator.Number;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

/**
* 应用信息 查询参数
* @author yibingzhou
* email:zhouyibing_1990@163.com
* AUTO-GENERATE BY PROJECT-BUILDER
**/
@ApiModel("应用信息查询参数")
@Data
public class AppInfoParam extends ManagedParam<Long> {

	@ApiModelProperty("系统id")
	@Number(minLen = 3, maxLen = 3, message = "系统id长度为3位数字")
	private String systemId;

	@ApiModelProperty("服务名称")
	private String serviceName;

	@ApiModelProperty("系统名称")
	private String systemName;

	@ApiModelProperty("服务id")
	@Number(minLen = 3, maxLen = 3, message = "服务id长度为3位数字")
	private String serviceId;

	@ApiModelProperty("负责人")
	private String manager;

	@ApiModelProperty("负责人id")
	private Long managerId;

	@ApiModelProperty("负责人手机")
	@Number(minLen = 11, maxLen = 11, message = "手机号格式错误")
	private String managerMobile;

	@ApiModelProperty("负责人邮箱")
	@Email(message = "邮箱格式错误")
	private String managerEmail;

	@ApiModelProperty("候选人")
	private String candidate;

	@ApiModelProperty("候选人id")
	private Long candidateId;

	@ApiModelProperty("负责人手机")
	@Number(minLen = 11, maxLen = 11, message = "手机号格式错误")
	private String candidateMobile;

	@ApiModelProperty("负责人邮箱")
	@Email(message = "邮箱格式错误")
	private String candidateEmail;

	@ApiModelProperty("应用id(system_id，service_id组合)")
	@Number(minLen = 6, maxLen = 6, message = "应用id长度为6位数字")
	private String appId;

	@ApiModelProperty("状态")
	private Integer status;

	@ApiModelProperty("应用描述")
	private String description;

	@ApiModelProperty("接口文档地址")
	private String docUrl;
}