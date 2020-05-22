package com.yipeng.baseservice.authority.service;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yipeng.baseservice.authority.constant.AuthStatus;
import com.yipeng.baseservice.authority.dao.RightsDao;
import com.yipeng.baseservice.authority.dao.UserRightsDao;
import com.yipeng.baseservice.authority.model.db.RightsModel;
import com.yipeng.baseservice.authority.model.db.RoleModel;
import com.yipeng.baseservice.authority.model.db.UserRightsModel;
import com.yipeng.baseservice.authority.model.db.UserRoleModel;
import com.yipeng.baseservice.authority.param.UserRightsParam;
import com.yipeng.baseservice.authority.result.ThinRights;
import com.yipeng.baseservice.authority.result.ThinRole;
import com.yipeng.framework.core.constants.BooleanEnum;
import com.yipeng.framework.core.constants.Constants;
import com.yipeng.framework.core.model.db.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.SwaggerDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yipeng.framework.core.service.BaseService;
import springfox.documentation.annotations.ApiIgnore;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
* @author yibingzhou
* email:zhouyibing_1990@163.com
* AUTO-GENERATE BY PROJECT-BUILDER
**/
@Service
public class RightsService extends BaseService<RightsModel, RightsDao> {

    @Autowired
    @JsonIgnore
    private UserRightsDao userRightsDao;


    public void disfranchise(String appId, String updaterId, List<Long> rightsIdList) {
        changeRightsStatus(appId, updaterId, rightsIdList, AuthStatus.DISABLED);
    }

    public void enableRights(String appId,String updaterId, List<Long> rightsIdList) {
        changeRightsStatus(appId, updaterId, rightsIdList, AuthStatus.ENABLED);
    }

    private void changeRightsStatus(String appId, String updaterId, List<Long> rightsIdList, AuthStatus status) {
        RightsModel update = new RightsModel();
        update.setUpdaterId(updaterId);
        update.setStatus(status.getCode().byteValue());
        Example example = new Example(RightsModel.class);
        example.createCriteria().andEqualTo("appId", appId).andIn(update.primaryKeyName(), rightsIdList);
        updateByExample(update, example);
    }

    public void grantUserRights(UserRightsParam userRightsParam) {
        Example example = new Example(UserRightsModel.class);
        example.createCriteria().andEqualTo("userId", userRightsParam.getUserId()).andEqualTo("rightsId", userRightsParam.getRightsId());
        UserRightsModel update = new UserRightsModel();
        AuthStatus newStatus = AuthStatus.ENABLED.codeOf(userRightsParam.getStatus());
        update.setStatus(newStatus.getCode().byteValue());
        update.setUpdaterId(userRightsParam.getUpdaterId());
        update.setTtl(userRightsParam.getTtl() == null ? null : userRightsParam.getTtl());
        Integer affectRows = userRightsDao.updateByExampleSelective(update, example);
        if(Constants.NO_AFFECT_ROWS.equals(affectRows)) {
            update.setRightsId(userRightsParam.getRightsId());
            update.setUserId(userRightsParam.getUserId());
            update.setCreatorId(userRightsParam.getCreatorId());
            userRightsDao.insert(update);
        }
    }

    public void revokeUserRights(UserRightsParam userRightsParam) {
        Example example = new Example(UserRightsModel.class);
        example.createCriteria().andEqualTo("userId", userRightsParam.getUserId()).andEqualTo("rightsId", userRightsParam.getRightsId());
        UserRightsModel update = new UserRightsModel();
        update.setStatus(AuthStatus.DISABLED.getCode().byteValue());
        update.setUpdaterId(userRightsParam.getUpdaterId());
        userRightsDao.updateByExampleSelective(update, example);
    }

    public List<ThinRights> queryUserRights(String appId, Long userId) {
        Example userRightsExample = new Example(UserRightsModel.class);
        userRightsExample.createCriteria().andEqualTo("userId", userId);
        List<Long> rightsIds = new ArrayList<>();
        Optional.ofNullable(userRightsDao.queryByExample(userRightsExample))
                .ifPresent(userRightsModels -> userRightsModels.forEach(userRightsModel -> rightsIds.add(userRightsModel.getRightsId())));

        Example rightsExample = new Example(RightsModel.class);
        rightsExample.createCriteria().andIn(BaseModel.ID, rightsIds)
                .andEqualTo("appId", appId)
                .andEqualTo(BaseModel.LOGIC_DELETE, BooleanEnum.FALSE.getCode())
                .andEqualTo("status", AuthStatus.ENABLED.getCode().byteValue());
        List<ThinRights> thinRights = queryByExample(rightsExample, ThinRights.class);
        return thinRights;
    }
}