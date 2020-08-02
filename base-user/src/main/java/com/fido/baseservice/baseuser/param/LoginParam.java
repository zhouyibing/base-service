package com.fido.baseservice.baseuser.param;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 登录参数
 * @author: yibingzhou
 */
@Data
public class LoginParam {

    /** 登录类型*/
    @NotBlank
    private Integer loginType;

    /** 身份标识（用户编号/手机号/邮箱等唯一标识）*/
    @NotBlank
    private String identify;

    /** 手机验证码/邮箱验证码*/
    private String verifyCode;

    /** 图片验证码*/
    private String imgVerifyCode;

    /** 图片验证码key*/
    private String imgKey;

    /** 登录密码*/
    private String password;

    /** 平台类型（pc/h5/app/weixin/minp/）*/
    private String platform;

    /** 系统类型(android/ios)*/
    private String os;

    /** 其他信息*/
    private String otherInfo;

    /** 回调地址（微信）*/
    private String returnUrl;
}
