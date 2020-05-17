package com.yipeng.baseservice.baseuser.constant;

import com.yipeng.framework.core.constants.CodeDesEnum;
import lombok.Getter;

/**
 * 用户注册类型
 * @author: yibingzhou
 */
public enum RegistryType implements CodeDesEnum<Integer, RegistryType> {
    //用户编号-手机号-验证码-密码注册
    USER_NO_MOBILE(1, "用户编号-手机号-验证码-密码"),
    //手机号-验证码登录
    MOBILE_VERIFY_CODE(2, "手机号-验证码"),
    //邮箱-密码
    EMAIL_PASS(3,"邮箱-密码"),
    //邮箱-验证码
    EMAIL_VERIFY_CODE(4, "邮箱-验证码"),
    //qq注册
    QQ(5, "QQ注册"),
    //微信注册
    WECHAT(6, "微信注册"),
    //微博注册
    WEIBO(7, "微博注册");

    @Getter
    private Integer code;

    @Getter
    private String description;

    RegistryType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public RegistryType[] all() {
        return values();
    }
}
