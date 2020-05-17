package com.yipeng.baseservice.authority.model.db;
import lombok.Data;
import java.io.Serializable;
import com.yipeng.framework.core.model.db.BaseModel;
import javax.persistence.*;
import java.util.Date;
/**
* 权限
* @author yibingzhou
* email:zhouyibing_1990@163.com
* AUTO-GENERATE BY PROJECT-BUILDER
**/
@Data
@Table(name = "b_auth_rights")
public class RightsModel extends BaseModel<Long> implements Serializable {
	private static final long serialVersionUID = 3984469199707321856L;


	/** 权限代码 */
	@Column(name = "rights_code")
	private String rightsCode;

	/** 备注说明 */
	@Column(name = "remark")
	private String remark;

	/** 权限名 */
	@Column(name = "rights_name")
	private String rightsName;

	/** 资源地址(当rights_type为资源型时) */
	@Column(name = "resource_address")
	private String resourceAddress;

	/** 默认存活时间(-1永久有效) */
	@Column(name = "default_ttl")
	private Integer defaultTtl;

	/** 创建者 */
	@Column(name = "creator_id")
	private String creatorId;

	/** 更新者 */
	@Column(name = "updater_id")
	private String updaterId;

	/** 权限类型(1:资源型，2:功能型，3:数据型) */
	@Column(name = "rights_type")
	private Byte rightsType;

	/** 应用id */
	@Column(name = "app_id")
	private String appId;

	/** 状态(0:禁用，1:启用) */
	@Column(name = "status")
	private Byte status;
}