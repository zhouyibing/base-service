package com.yipeng.baseservice.authority.model.db;
import lombok.Data;
import java.io.Serializable;
import com.yipeng.framework.core.model.db.BaseModel;
import javax.persistence.*;
import java.util.Date;
/**
* 用户特殊权限（不赋角色，直接赋权）
* @author yibingzhou
* email:zhouyibing_1990@163.com
* AUTO-GENERATE BY PROJECT-BUILDER
**/
@Data
@Table(name = "b_auth_user_rights")
public class UserRightsModel extends BaseModel<Long> implements Serializable {
	private static final long serialVersionUID = 2785907946497814528L;


	/** 权限id */
	@Column(name = "rights_id")
	private Long rightsId;

	/** 用户id */
	@Column(name = "user_id")
	private Long userId;

	/** 创建者 */
	@Column(name = "creator_id")
	private String creatorId;

	/** 更新者 */
	@Column(name = "updater_id")
	private String updaterId;

	/** 有效期(当status=2，临时启用时） */
	@Column(name = "ttl")
	private Long ttl;

	/** 状态(0:禁用，1:启用，2:临时启用) */
	@Column(name = "status")
	private Byte status;
}