package com.yipeng.baseservice.authority.model.db;
import lombok.Data;
import java.io.Serializable;
import com.yipeng.framework.core.model.db.BaseModel;
import javax.persistence.*;
import java.util.Date;
/**
* 角色
* @author yibingzhou
* email:zhouyibing_1990@163.com
* AUTO-GENERATE BY PROJECT-BUILDER
**/
@Data
@Table(name = "b_auth_role")
public class RoleModel extends BaseModel<Long> implements Serializable {
	private static final long serialVersionUID = 3610818042703493120L;


	/** 角色名称 */
	@Column(name = "role_name")
	private String roleName;

	/** 默认存活时间(-1永久有效) */
	@Column(name = "default_ttl")
	private Integer defaultTtl;

	/** 创建者 */
	@Column(name = "creator_id")
	private String creatorId;

	/** 更新者 */
	@Column(name = "updater_id")
	private String updaterId;

	/** 角色代码 */
	@Column(name = "role_code")
	private String roleCode;

	/** 备注说明 */
	@Column(name = "remark")
	private String remark;

	/** 应用id */
	@Column(name = "app_id")
	private String appId;

	/** 状态(0:禁用，1:启用) */
	@Column(name = "status")
	private Byte status;
}