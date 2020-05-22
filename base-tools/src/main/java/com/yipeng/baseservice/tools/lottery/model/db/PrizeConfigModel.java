package com.yipeng.baseservice.tools.lottery.model.db;
import lombok.Data;
import java.io.Serializable;
import com.yipeng.framework.core.model.db.BaseModel;
import javax.persistence.*;

/**
* 活动奖品配置
* @author yibingzhou
* email:zhouyibing_1990@163.com
* AUTO-GENERATE BY PROJECT-BUILDER
**/
@Data
@Table(name = "l_prize_config")
public class PrizeConfigModel extends BaseModel<Long> implements Serializable {
	private static final long serialVersionUID = 8069864113068662784L;


	/** 排序 */
	@Column(name = "sequence")
	private Integer sequence;

	/** 奖品id */
	@Column(name = "prize_id")
	private Long prizeId;

	/** 中奖概率 */
	@Column(name = "probability")
	private Double probability;

	/** 奖品剩余数量 */
	@Column(name = "remain")
	private Integer remain;

	/** 奖项分类(一等奖，二等奖，特等奖等） */
	@Column(name = "prize_category")
	private String prizeCategory;

	/** 奖品数量 */
	@Column(name = "num")
	private Integer num;

	/** 活动id */
	@Column(name = "activity_id")
	private Long activityId;
}