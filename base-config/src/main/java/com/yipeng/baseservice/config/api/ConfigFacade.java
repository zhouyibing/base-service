package com.yipeng.baseservice.config.api;

import com.github.pagehelper.PageInfo;
import com.yipeng.baseservice.config.param.ConfigParam;
import com.yipeng.baseservice.config.param.ConfigQueryParam;
import com.yipeng.baseservice.config.result.ConfigResult;
import com.yipeng.framework.common.model.ValidList;
import com.yipeng.framework.common.param.PageParam;
import com.yipeng.framework.common.result.Result;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 配置项查询接口
 * @author: yibingzhou
 */
public interface ConfigFacade {

    /**
     * 获取配置
     * @return
     */
    Result<List<ConfigResult>> get(ConfigQueryParam configQueryParam);

    /**
     * 分页查询配置项
     * @return
     */
    PageInfo<ConfigResult> page(PageParam<ConfigQueryParam> pageParam);

    /**
     * 删除配置
     * @return
     */
    Result<Integer> delete(ConfigQueryParam param);

    Result<Boolean> deleteById(Long id);
    Result<Integer> deleteByIdList(List<Long> id);

    /**
     * 更新配置
     * @return
     */
    Result<Boolean> update(ConfigParam param);

    /**
     * 保存配置
     * @return
     */
    Result<Boolean> save(ConfigParam configParam);

    /**
     * 批量保存配置
     * @return
     */
    Result<Integer> saveList(ValidList<ConfigParam> configList);
}
