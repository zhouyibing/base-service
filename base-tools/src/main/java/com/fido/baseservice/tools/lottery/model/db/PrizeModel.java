package com.fido.baseservice.tools.lottery.model.db;
import lombok.Data;
import java.io.Serializable;
import com.fido.framework.core.model.db.BaseModel;
import javax.persistence.*;

/**
* 奖品
* @author yibingzhou
* email:zhouyibing_1990@163.com
* AUTO-GENERATE BY PROJECT-BUILDER
**/
@Data
@Table(name = "l_prize")
public class PrizeModel extends BaseModel<Long> implements Serializable {
	private static final long serialVersionUID = 5164585577871429632L;


	/** 图片地址 */
	@Column(name = "img_url")
	private String imgUrl;

	/** 奖品名称 */
	@Column(name = "name")
	private String name;
}