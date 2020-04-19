package com.yipeng.baseservice.sns.api;

/**
 * 微信相关接口
 * @author: yibingzhou
 */
public interface WechatFacade {

    /**
     * 微信授权
     */
    void authorize();

    /**
     * 同步微信息
     * @param openId
     * @param userId
     */
    void syncWechatInfo(String openId, Long userId);

    void getWechatInfo(Long userId);


}