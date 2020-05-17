package com.yipeng.baseservice.app.model.db;
import lombok.Data;
import java.io.Serializable;
import com.yipeng.framework.core.model.db.BaseModel;
import javax.persistence.*;
import java.util.Date;
/**
* 应用信息
* @author yibingzhou
* email:zhouyibing_1990@163.com
* AUTO-GENERATE BY PROJECT-BUILDER
**/
@Data
@Table(name = "b_app_info")
public class AppInfoModel extends BaseModel<Long> implements Serializable {
	private static final long serialVersionUID = 8869062742002746368L;


	/** 系统id */
	@Column(name = "system_id")
	private String systemId;

	/** 服务名称 */
	@Column(name = "service_name")
	private String serviceName;

	/** 系统名称 */
	@Column(name = "system_name")
	private String systemName;

	/** 描述 */
	@Column(name = "description")
	private String description;

	/** 服务id */
	@Column(name = "service_id")
	private String serviceId;

	/** 创建者 */
	@Column(name = "creator_id")
	private String creatorId;

	/** 更新者 */
	@Column(name = "updater_id")
	private String updaterId;

	/** 应用id(system_id，service_id组合) */
	@Column(name = "app_id")
	private String appId;

	/** 状态 */
	@Column(name = "status")
	private Integer status;

	/** 负责人 */
	@Column(name = "manager")
	private String manager;

	/** 负责人id */
	@Column(name = "manager_id")
	private Long managerId;

	/** 负责人手机 */
	@Column(name = "manager_mobile")
	private String managerMobile;

	/** 负责人email */
	@Column(name = "manager_email")
	private String managerEmail;

	/** 候选人 */
	@Column(name = "candidate")
	private String candidate;

	/** 候选人id */
	@Column(name = "candidate_id")
	private Long candidateId;

	/** 候选人手机 */
	@Column(name = "candidate_mobile")
	private String candidateMobile;

	/** 候选人email */
	@Column(name = "candidate_email")
	private String candidateEmail;
}