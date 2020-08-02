package com.fido.baseservice.sns.api;

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

    /**
     * 获取用户微信信息
     * @param userId 用户id
     */
    void getWechatInfo(Long userId);


}