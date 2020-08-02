package com.fido.baseservice.app.model.db;
import lombok.Data;
import java.io.Serializable;
import com.fido.framework.core.model.db.BaseModel;
import javax.persistence.*;

/**
* 应用实例
* @author yibingzhou
* email:zhouyibing_1990@163.com
* AUTO-GENERATE BY PROJECT-BUILDER
**/
@Data
@Table(name = "b_app_instance")
public class AppInstanceModel extends BaseModel<Long> implements Serializable {
	private static final long serialVersionUID = 4379524003939358208L;


	/** 端口 */
	@Column(name = "port")
	private Integer port;

	/** ip */
	@Column(name = "ip")
	private String ip;

	/** 应用id */
	@Column(name = "app_id")
	private String appId;

	/** 状态 */
	@Column(name = "status")
	private Integer status;
}