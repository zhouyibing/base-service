package com.fido.baseservice.app.service;
import com.fido.baseservice.app.constant.AppInfoErrorCode;
import com.fido.baseservice.app.constant.AppStatus;
import com.fido.baseservice.app.dao.AppInfoDao;
import com.fido.baseservice.app.model.db.AppInfoModel;
import com.fido.baseservice.app.param.AppInfoParam;
import com.fido.baseservice.app.result.AppInfoResult;
import com.fido.framework.core.exception.ExceptionUtil;
import com.fido.framework.core.model.db.AccessObject;
import org.springframework.stereotype.Service;
import com.fido.framework.core.service.BaseService;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
* @author yibingzhou
* email:zhouyibing_1990@163.com
* AUTO-GENERATE BY PROJECT-BUILDER
**/
@Service
public class AppInfoService extends BaseService<AppInfoModel, AppInfoDao> {

    public AppInfoResult getAppInfo(String appId) {
        List<AppInfoResult> appInfoResultList = queryAllMatch(new Object[]{"appId", appId}, AppInfoResult.class);
        if(CollectionUtils.isEmpty(appInfoResultList)) {
            throw ExceptionUtil.doThrow(AppInfoErrorCode.APP_UNREGISTERED);
        }
        return appInfoResultList.get(0);
    }

    @Override
    public boolean createIfAbsent(AccessObject query, AccessObject create) throws ExceptionUtil.BizException {
        AppInfoParam queryParam = new AppInfoParam();
        queryParam.setServiceId(((AppInfoParam)query).getServiceId());
        queryParam.setSystemId(((AppInfoParam)query).getSystemId());
        ((AppInfoParam)create).setAppId(((AppInfoParam) create).getSystemId().concat(((AppInfoParam) create).getServiceId()));
        ((AppInfoParam)create).setStatus(AppStatus.NORMAL.getCode());
        ((AppInfoParam)create).setId(null);
        return super.createIfAbsent(queryParam, create);
    }

    public List<AppInfoResult> getAll() {
        Example example = new Example(AppInfoModel.class);
        example.createCriteria().andGreaterThanOrEqualTo("status", 0);
        return queryByExample(example, AppInfoResult.class);
    }
}