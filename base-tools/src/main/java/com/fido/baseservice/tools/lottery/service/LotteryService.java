package com.fido.baseservice.tools.lottery.service;

import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.fido.baseservice.tools.lottery.dao.PrizeConfigDao;
import com.fido.baseservice.tools.lottery.dao.PrizeDao;
import com.fido.baseservice.tools.lottery.dao.PrizeWinningRecordDao;
import com.fido.baseservice.tools.lottery.model.db.PrizeConfigModel;
import com.fido.baseservice.tools.lottery.model.db.PrizeModel;
import com.fido.baseservice.tools.lottery.model.db.PrizeWinningRecordModel;
import com.fido.framework.core.exception.ErrorCode;
import com.fido.framework.core.exception.ExceptionUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 抽奖服务
 * @author: yibingzhou
 */
@Service
public class LotteryService {

    @Resource
    private PrizeWinningRecordDao prizeWinningRecordDao;
    @Resource
    private PrizeDao prizeDao;
    @Resource
    private PrizeConfigDao prizeConfigDao;

    private Map<Long, WeightRandom> acvitityConfig = new ConcurrentHashMap<>();

    private LoadingCache<Long, PrizeModel> prizeCache = CacheBuilder.newBuilder()
            .refreshAfterWrite(5, TimeUnit.MINUTES)
            .expireAfterWrite(6, TimeUnit.MINUTES)
            .build(new CacheLoader<Long, PrizeModel>() {

                @Override
                public PrizeModel load(Long prizeId) throws Exception {
                    return prizeDao.queryByPk(prizeId);
                }
            });

    /**
     * 抽奖入口
     * @param userId
     * @param activityId
     * @return
     */
    @Transactional
    public PrizeModel luckyDraw(Long userId, Long activityId) {
        //1.判断用户是否抽过奖
        if(hasParticipated(userId, activityId)) {
            throw ExceptionUtil.doThrow(ErrorCode.BIZ_ERROR.msg("已参加过抽奖"));
        }
        //2.概率随机抽取奖品
        PrizeModel prize = probabilityRandom(activityId);
        //3.保存抽奖纪录
        saveWinningRecord(userId, activityId, prize.getId());
        return prize;
    }

    /**
     * 抽取指定奖品的幸运儿
     * @param activityId
     * @param prizeId
     * @return
     */
    @Transactional
    public Long selectLuckPerson(Long activityId, Long prizeId, List<Long> userIds) {
        PrizeConfigModel configParam = new PrizeConfigModel();
        configParam.setActivityId(activityId);
        configParam.setPrizeId(prizeId);
        PrizeConfigModel result = prizeConfigDao.queryOne(configParam);
        if(null == result || result.getRemain() <= 0) {
            throw ExceptionUtil.doThrow(ErrorCode.BIZ_ERROR.msg("奖品"+prizeId+"已抽完"));
        }
        //1.除去已抽中的人
        Example example = new Example(PrizeWinningRecordModel.class);
        example.createCriteria().andEqualTo("activityId", activityId).andIn("userId", userIds);
        List<PrizeWinningRecordModel> winningRecords = prizeWinningRecordDao.queryByExample(example);
        winningRecords.forEach(record -> userIds.remove(record.getUserId()));

        //2.随机从剩余用户列表里选取一个用户
        int ix = RandomUtil.getRandom().nextInt(userIds.size());
        while (userIds.size() > 0 ) {
            //3.判断用户是否已中奖
            PrizeWinningRecordModel param = new PrizeWinningRecordModel();
            param.setUserId(userIds.get(ix));
            param.setActivityId(activityId);
            if (CollectionUtils.isEmpty(prizeWinningRecordDao.queryList(param))) {
                saveWinningRecord(userIds.get(ix), activityId, prizeId);
                break;
            } else {
                //删除已抽中用户，继续抽
                userIds.remove(ix);
                ix = RandomUtil.getRandom().nextInt(userIds.size());
            }
        }
        if(userIds.size() == 0) {
           throw ExceptionUtil.doThrow(ErrorCode.BIZ_ERROR.msg("抽奖已结束"));
        }
        //4.更新库存，更新获奖记录
        int remain = deductInventory(result);
        if(remain < 0 ){
            throw ExceptionUtil.doThrow(ErrorCode.BIZ_ERROR.msg("奖品"+prizeId+"已抽完"));
        }
        return userIds.get(ix);
    }

    /**
     * 抽取指定奖品分类里的幸运儿
     * @param activityId
     * @param prizeCategory
     * @return
     */
    public PrizeWinningRecordModel selectLuckPerson(Long activityId, String prizeCategory, List<Long> userIds) {
        PrizeConfigModel prizeConfig = new PrizeConfigModel();
        prizeConfig.setActivityId(activityId);
        prizeConfig.setPrizeCategory(prizeCategory);
        List<PrizeConfigModel> prizeConfigs = prizeConfigDao.queryList(prizeConfig);
        int prizeIx = RandomUtil.getRandom().nextInt(prizeConfigs.size());
        for(int i=0;i<prizeConfigs.size();i++) {
            try {
                //TODO 需验证selectLuckPerson事务是否生效
                Long userId = this.selectLuckPerson(activityId, prizeConfigs.get(prizeIx).getPrizeId(), userIds);
                PrizeWinningRecordModel winningRecord = new PrizeWinningRecordModel();
                winningRecord.setUserId(userId);
                winningRecord.setPrizeId(prizeConfigs.get(prizeIx).getPrizeId());
                PrizeModel prize = prizeDao.queryByPk(prizeConfigs.get(prizeIx).getPrizeId());
                winningRecord.setPrizeName(prize == null ? null : prize.getName());
                return winningRecord;
            } catch (ExceptionUtil.BizException e) {
                if(prizeConfigs.size()==1) {
                    throw e;
                }
            }
            int newPrize = RandomUtil.getRandom().nextInt(prizeConfigs.size());
            while(newPrize == prizeIx) {
                newPrize = RandomUtil.getRandom().nextInt(prizeConfigs.size());
            }
            prizeIx = newPrize;
        }
        throw ExceptionUtil.doThrow(ErrorCode.BIZ_ERROR.msg(prizeCategory+"已抽完"));
    }

    /**
     * 中奖纪录查询
     * @param activityId
     * @return
     */
    public PageInfo<PrizeWinningRecordModel> winningRecords(Long activityId, int pageNum, int pageSize) {
        PrizeWinningRecordModel winningRecord = new PrizeWinningRecordModel();
        winningRecord.setActivityId(activityId);
        PageInfo<PrizeWinningRecordModel> pageInfo = prizeWinningRecordDao.queryPage(winningRecord, pageNum, pageSize);
        List<PrizeWinningRecordModel> winningRecords = pageInfo.getList();
        if(CollectionUtils.isEmpty(winningRecords)) {
            return pageInfo;
        }
        List<Long> prizeIds = new ArrayList<>();
        winningRecords.forEach(record -> prizeIds.add(record.getPrizeId()));
        if(CollectionUtils.isEmpty(prizeIds)) {
            return pageInfo;
        }
        Example prizeExample = new Example(PrizeModel.class);
        prizeExample.createCriteria().andIn("id", prizeIds);
        List<PrizeModel> prizes = prizeDao.queryByExample(prizeExample);
        Map<Long, PrizeModel> prizeMap = prizes.stream().collect(Collectors.toMap(prize -> prize.getId(), prize -> prize));
        winningRecords.forEach(record -> {
            PrizeModel prize = prizeMap.get(record.getPrizeId());
            record.setPrizeName( prize == null ? null : prize.getName());
        });
        return pageInfo;
    }

    /**
     * 获取奖品列表
     * @param activityId
     * @return
     */
    public List<PrizeModel> getPrizeList(Long activityId) {
        PrizeConfigModel prizeConfig = new PrizeConfigModel();
        prizeConfig.setActivityId(activityId);
        List<PrizeConfigModel> prizeConfigs = prizeConfigDao.queryList(prizeConfig);
        List<Long> prizeIds = prizeConfigs.stream().map(config -> config.getPrizeId()).collect(Collectors.toList());
        if( !CollectionUtils.isEmpty(prizeIds)) {
            Example example = new Example(PrizeModel.class);
            example.createCriteria().andIn("id", prizeIds);
            return prizeDao.queryByExample(example);
        }
        return null;
    }

    /**
     * 获取奖品列表
     * @param activityId
     * @return
     */
    public List<PrizeModel> getPrizeList(Long activityId, String prizeCategory) {
        PrizeConfigModel prizeConfig = new PrizeConfigModel();
        prizeConfig.setActivityId(activityId);
        prizeConfig.setPrizeCategory(prizeCategory);
        List<PrizeConfigModel> prizeConfigs = prizeConfigDao.queryList(prizeConfig);
        List<Long> prizeIds = prizeConfigs.stream().map(config -> config.getPrizeId()).collect(Collectors.toList());
        if( !CollectionUtils.isEmpty(prizeIds)) {
            Example example = new Example(PrizeModel.class);
            example.createCriteria().andIn("id", prizeIds);
            return prizeDao.queryByExample(example);
        }
        return null;
    }


    private int deductInventory(PrizeConfigModel config) {
        if(config.getRemain()<=0) {
            return -1;
        }
        PrizeConfigModel prizeConfig = new PrizeConfigModel();
        prizeConfig.setRemain(config.getRemain() - 1);
        Example example = new Example(PrizeConfigModel.class);
        example.createCriteria().andEqualTo("id", config.getId()).andEqualTo("remain", config.getRemain());
        while (0==prizeConfigDao.updateByExample(prizeConfig, example)) {
            config = prizeConfigDao.queryByPk(config.getId());
            if(config.getRemain() <= 0) {
                return config.getRemain() -1;
            }
            prizeConfig.setRemain(config.getRemain() -1);
            example = new Example(PrizeConfigModel.class);
            example.createCriteria().andEqualTo("id", config.getId()).andEqualTo("remain", config.getRemain());
        }
        return prizeConfig.getRemain();
    }

    private void saveWinningRecord(Long userId, Long activityId, Long prizeId) {
        PrizeWinningRecordModel winningRecord = new PrizeWinningRecordModel();
        winningRecord.setActivityId(activityId);
        winningRecord.setUserId(userId);
        winningRecord.setPrizeId(prizeId);
        prizeWinningRecordDao.insert(winningRecord);
    }

    private PrizeModel probabilityRandom(Long activityId) {
        //1.从数据库获得活动奖品配置
        List<PrizeConfigModel> prizeConfigList = null;
        PrizeConfigModel param = new PrizeConfigModel();
        param.setActivityId(activityId);
        prizeConfigList = prizeConfigDao.queryList(param);

        if(CollectionUtils.isEmpty(prizeConfigList)) {
            throw ExceptionUtil.doThrow(ErrorCode.BIZ_ERROR.msg("活动无效"));
        }
        AtomicInteger notEmpthCount = new AtomicInteger(0);
        PrizeConfigModel lastNotEmpty = null;
        for(int i=0;i<prizeConfigList.size();i++) {
            if(prizeConfigList.get(i).getRemain() > 0) {
                notEmpthCount.incrementAndGet();
                lastNotEmpty = prizeConfigList.get(i);
            }
        }
        if(notEmpthCount.get() == 0) {
            throw ExceptionUtil.doThrow(ErrorCode.BIZ_ERROR.msg("所有奖品已抽完"));
        }
        PrizeConfigModel prizeConfig = null;
        if(notEmpthCount.get() ==1) {
            prizeConfig = lastNotEmpty;
        } else {
            WeightRandom weightRandom = acvitityConfig.get(activityId);
            if(weightRandom == null) {
                weightRandom = new WeightRandom(prizeConfigList);
                acvitityConfig.put(activityId, weightRandom);
            }
            prizeConfig = weightRandom.random();
        }

        //2.从数据库获得奖品信息
        PrizeModel prize = null;
        try {
            prize = prizeCache.get(prizeConfig.getPrizeId());
            //3.扣减库存
            int remain = deductInventory(prizeConfig);
            if(remain < 0) {
                prize = probabilityRandom(activityId);
            }
        } catch (ExecutionException e) {
            throw ExceptionUtil.doThrow(ErrorCode.BIZ_ERROR.msg("获取奖品信息异常"));
        }
        return prize;
    }

    private boolean hasParticipated(Long userId, Long activityId) {
        PrizeWinningRecordModel winningRecord = new PrizeWinningRecordModel();
        winningRecord.setUserId(userId);
        winningRecord.setActivityId(activityId);
        return null != prizeWinningRecordDao.queryOne(winningRecord);
    }

    public class WeightRandom {
        private TreeMap<Double, PrizeConfigModel> weightMap = new TreeMap<>();

        public WeightRandom(List<PrizeConfigModel> list) {
            Preconditions.checkNotNull(list, "list can NOT be null!");
            for (PrizeConfigModel prizeConfig : list) {
                double lastWeight = this.weightMap.size() == 0 ? 0 : this.weightMap.lastKey().doubleValue();
                this.weightMap.put(prizeConfig.getProbability() + lastWeight, prizeConfig);//权重累加
            }
        }

        public PrizeConfigModel random() {
            double randomWeight = this.weightMap.lastKey() * Math.random();
            SortedMap<Double, PrizeConfigModel> tailMap = this.weightMap.tailMap(randomWeight, false);
            return this.weightMap.get(tailMap.firstKey());
        }
    }
}
