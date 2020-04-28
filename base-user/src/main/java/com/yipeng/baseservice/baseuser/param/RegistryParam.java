package com.yipeng.baseservice.baseuser.param;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 注册参数
 * @author: yibingzhou
 */
@Data
public class RegistryParam extends LoginParam{

    /** 注册方式*/
    @NotBlank
    private Integer registryType;

    /** 邀请码/邀请人编号*/
    private String recommendCode;
}
