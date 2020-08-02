package com.fido.baseservice.app.service;
import com.fido.baseservice.app.constant.AppInfoConstants;
import com.fido.baseservice.app.constant.AppStatus;
import com.fido.baseservice.app.dao.AppInstanceDao;
import com.fido.baseservice.app.model.db.AppInstanceModel;
import com.fido.framework.core.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.fido.framework.core.service.BaseService;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
* @author yibingzhou
* email:zhouyibing_1990@163.com
* AUTO-GENERATE BY PROJECT-BUILDER
**/
@Service
@Slf4j
public class AppInstanceService extends BaseService<AppInstanceModel, AppInstanceDao> {

    public void ping(String appId, String ip, Integer port) {
        AppInstanceModel query = new AppInstanceModel();
        query.setAppId(appId);
        query.setIp(ip);
        query.setPort(port);
        AppInstanceModel update = new AppInstanceModel();
        update.setStatus(AppStatus.RUNNING.getCode());
        Integer rows = updateAllMatch(query, update);
        if(Constants.NO_AFFECT_ROWS.equals(rows)) {
            query.setStatus(AppStatus.RUNNING.getCode());
            save(query);
        }
    }

    /**
     * 每隔一分钟进行健康检查
     */
    @Scheduled(initialDelayString = "${base-app.healthCheckInitial:5000}", fixedDelayString = "${base-app.healthCheckInterval:60000}")
    public void healthCheck() {
        AppInstanceModel param = new AppInstanceModel();
        param.setStatus(AppStatus.RUNNING.getCode());
        List<AppInstanceModel> appInstanceList = this.queryAllMatch(param, AppInstanceModel.class);
        if (!CollectionUtils.isEmpty(appInstanceList)) {
            List<Long> stopInstances = new ArrayList<>();
            appInstanceList.forEach(appInstance -> {
                if (System.currentTimeMillis() - appInstance.getUpdateTime().getTime() > AppInfoConstants.HEARTBEAT_TIMEWAIT) {
                    //一分钟了还没有心跳，则标记状态为‘停止’
                    stopInstances.add(appInstance.getId());
                    log.warn("server:[{}],not survive", appInstance);
                }
            });
            if (!CollectionUtils.isEmpty(stopInstances)) {
                param.setStatus(AppStatus.STOP.getCode());
                Example example = new Example(AppInstanceModel.class);
                example.createCriteria().andIn(param.primaryKeyName(), stopInstances);
                updateByExample(param, example);
            }
        }
    }

    public void disconnect(String appId, String ip, Integer port) {
        AppInstanceModel query = new AppInstanceModel();
        query.setAppId(appId);
        query.setIp(ip);
        query.setPort(port);
        AppInstanceModel update = new AppInstanceModel();
        update.setStatus(AppStatus.STOP.getCode());
        updateAllMatch(query, update);
    }
}