package com.fido.baseservice.tools.lottery.model.db;
import lombok.Data;
import java.io.Serializable;
import com.fido.framework.core.model.db.BaseModel;
import javax.persistence.*;

/**
* 中奖纪录
* @author yibingzhou
* email:zhouyibing_1990@163.com
* AUTO-GENERATE BY PROJECT-BUILDER
**/
@Data
@Table(name = "l_prize_winning_record")
public class PrizeWinningRecordModel extends BaseModel<Long> implements Serializable {
	private static final long serialVersionUID = 7277876079398375424L;


	/** 用户id */
	@Column(name = "user_id")
	private Long userId;

	/** 奖品id */
	@Column(name = "prize_id")
	private Long prizeId;

	/** 活动id */
	@Column(name = "activity_id")
	private Long activityId;

	@Transient
	private String prizeName;
}