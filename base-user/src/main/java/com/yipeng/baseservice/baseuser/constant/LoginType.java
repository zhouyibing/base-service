package com.yipeng.baseservice.baseuser.constant;

import com.yipeng.framework.common.constants.CodeDesEnum;
import lombok.Getter;

/**
 * 登录类型
 * @author: yibingzhou
 */
public enum LoginType implements CodeDesEnum<Integer, LoginType> {
    USER_NO_PASS(1, "用户编号-密码"),
    MOBILE_VERIFY_CODE(2, "手机号-验证码"),
    EMAIL_PASS(3,"邮箱-密码"),
    EMAIL_VERIFY_CODE(4, "邮箱验证码"),
    QQ(5, "QQ登录"),
    WECHAT(6, "微信登录"),
    WEIBO(7, "微博登录"),
    QRCODE(8, "二维码登录");

    @Getter
    private Integer code;
    @Getter
    private String description;

    LoginType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public LoginType[] all() {
        return values();
    }
}
