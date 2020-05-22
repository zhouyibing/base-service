package com.yipeng.baseservice.authority.service;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yipeng.baseservice.authority.constant.AuthConstants;
import com.yipeng.baseservice.authority.constant.AuthStatus;
import com.yipeng.baseservice.authority.constant.AuthorityErrorCode;
import com.yipeng.baseservice.authority.dao.RoleDao;
import com.yipeng.baseservice.authority.dao.RoleRightsDao;
import com.yipeng.baseservice.authority.dao.UserRoleDao;
import com.yipeng.baseservice.authority.model.db.*;
import com.yipeng.baseservice.authority.param.RoleRightsParam;
import com.yipeng.baseservice.authority.param.UserRoleParam;
import com.yipeng.baseservice.authority.result.RoleResult;
import com.yipeng.baseservice.authority.result.ThinRights;
import com.yipeng.baseservice.authority.result.ThinRole;
import com.yipeng.baseservice.authority.result.UserPermissions;
import com.yipeng.framework.core.constants.BooleanEnum;
import com.yipeng.framework.core.constants.Constants;
import com.yipeng.framework.core.exception.ErrorCode;
import com.yipeng.framework.core.exception.ExceptionUtil;
import com.yipeng.framework.core.model.db.BaseModel;
import io.swagger.annotations.ApiModel;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yipeng.framework.core.service.BaseService;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* @author yibingzhou
* email:zhouyibing_1990@163.com
* AUTO-GENERATE BY PROJECT-BUILDER
**/
@Service
public class RoleService extends BaseService<RoleModel, RoleDao> {
    @Autowired
    @JsonIgnore
    private RoleRightsDao roleRightsDao;

    @Autowired
    @JsonIgnore
    private UserRoleDao userRoleDao;

    @Autowired
    @JsonIgnore
    private RightsService rightsService;

    public void empowerment(RoleRightsParam roleRightsParam) {
        //1.判断角色是否属于指定应用
        checkRole(roleRightsParam.getRoleId(), roleRightsParam.getAppId());
        List<RoleRightsParam.RightsItem> rightsItemList = roleRightsParam.getRights();
        Map<Long, RoleRightsParam.RightsItem> rightsItemMap = Maps.newHashMap();
        List<Long> rightsIdList = rightsItemList.stream().map(rightsItem -> {
            rightsItemMap.put(rightsItem.getRightsId(), rightsItem);
            return rightsItem.getRightsId();
        }).collect(Collectors.toList());
        //2.查询权限
        List<RightsModel> rightsModels = rightsService.queryByPks(rightsIdList, RightsModel.class);
        if(CollectionUtils.isEmpty(rightsModels)) {
            throw ExceptionUtil.doThrow(AuthorityErrorCode.NOT_FOUND_RIGHTS.errorParams(new Object[]{rightsIdList}));
        }
        List<RoleRightsModel> roleRightsModelList = Lists.newArrayListWithExpectedSize(rightsModels.size());
        //3.组装角色权限列表，并判断权限是否属于指定应用
        rightsModels.forEach(rightsModel -> {
            if(rightsModel.getAppId().equals(roleRightsParam.getAppId())) {
                throw ExceptionUtil.doThrow(AuthorityErrorCode.RIGHTS_NOT_BELONG.errorParams(new Object[]{rightsModel.getId(), roleRightsParam.getAppId()}));
            }
            RoleRightsModel roleRightsModel = new RoleRightsModel();
            roleRightsModel.setRoleId(roleRightsModel.getRoleId());
            roleRightsModel.setRightsId(rightsModel.getId());
            roleRightsModel.setCreatorId(roleRightsModel.getCreatorId());
            roleRightsModel.setUpdaterId(roleRightsModel.getUpdaterId());
            RoleRightsParam.RightsItem rightsItem = rightsItemMap.get(rightsModel.getId());
            //如果未指定权限使用期限，则使用权限库中默认的ttl
            roleRightsModel.setTtl(rightsItem.getTtl() == null ? rightsModel.getDefaultTtl() : rightsItem.getTtl());
            AuthStatus newStatus = AuthStatus.ENABLED.codeOf(rightsItem.getStatus());
            roleRightsModel.setStatus(newStatus.getCode().byteValue());
            roleRightsModelList.add(roleRightsModel);
        });
        roleRightsDao.insertList(roleRightsModelList);
    }

    public void disfranchise(RoleRightsParam roleRightsParam) {
        //1.判断角色是否属于指定应用
        checkRole(roleRightsParam.getRoleId(), roleRightsParam.getAppId());
        List<Long> rightsIdList = roleRightsParam.getRights().stream().map(rightsItem -> rightsItem.getRightsId()).collect(Collectors.toList());
        RoleRightsModel roleRightsModel = new RoleRightsModel();
        roleRightsModel.setStatus(AuthStatus.DISABLED.getCode().byteValue());
        roleRightsModel.setUpdaterId(roleRightsParam.getUpdaterId());
        Example example = new Example(RoleRightsModel.class);
        example.createCriteria().andEqualTo("roleId", roleRightsParam.getRoleId()).andIn("rightsId",rightsIdList);
        roleRightsDao.updateByExampleSelective(roleRightsModel, example);
    }

    public void enableRights(RoleRightsParam roleRightsParam) {
        //1.判断角色是否属于指定应用
        checkRole(roleRightsParam.getRoleId(), roleRightsParam.getAppId());
        List<Long> enableRoleRights = Lists.newArrayList();

        roleRightsParam.getRights().forEach(rightsItem -> {
            AuthStatus newStatus = AuthStatus.ENABLED.codeOf(rightsItem.getStatus());
            if(AuthStatus.DISABLED == newStatus) {
                throw ExceptionUtil.doThrow(ErrorCode.ILLEGAL_ARGUMENT.msg("非法状态"));
            }
            if(AuthStatus.TEMP_ENABLED == newStatus ) {
                //临时权限只能一个一个更新，因为涉及到多字段的批量更新，basemapper还不支持
                RoleRightsModel roleRightsModel = new RoleRightsModel();
                roleRightsModel.setUpdaterId(roleRightsParam.getUpdaterId());
                roleRightsModel.setStatus(newStatus.getCode().byteValue());
                roleRightsModel.setTtl(rightsItem.getTtl() == null ? null : rightsItem.getTtl());
                Example example = new Example(RoleRightsModel.class);
                example.createCriteria().andEqualTo("roleId", roleRightsParam.getRoleId()).andEqualTo("rightsId", rightsItem.getRightsId());
                roleRightsDao.updateByExampleSelective(roleRightsModel, example);
            } else {
                enableRoleRights.add(rightsItem.getRightsId());
            }
        });
        //永久权限可批量更新，因为状态和ttl字段都是一样的值
        if( !CollectionUtils.isEmpty(enableRoleRights)) {
            RoleRightsModel roleRightsModel = new RoleRightsModel();
            roleRightsModel.setStatus(AuthStatus.ENABLED.getCode().byteValue());
            roleRightsModel.setTtl(AuthConstants.PERMANENTLY);
            roleRightsModel.setUpdaterId(roleRightsParam.getUpdaterId());
            Example example = new Example(RoleRightsModel.class);
            example.createCriteria().andEqualTo("roleId", roleRightsParam.getRoleId()).andIn("rightsId",enableRoleRights);
            roleRightsDao.updateByExampleSelective(roleRightsModel, example);
        }
    }

    private void checkRole(Long roleId, String appId) {
        RoleModel roleModel = new RoleModel();
        roleModel.setId(roleId);
        roleModel.setAppId(appId);
        //1.判断角色是否属于指定应用
        if(!existAllMatch(roleModel)) {
            throw ExceptionUtil.doThrow(AuthorityErrorCode.ROLE_NOT_BELONG.errorParams(new Object[]{roleModel.getId(), roleModel.getAppId()}));
        }
    }

    public void grantRole(UserRoleParam userRoleParam) {
        Example example = new Example(UserRoleModel.class);
        example.createCriteria().andEqualTo("userId", userRoleParam.getUserId()).andEqualTo("roleId", userRoleParam.getRoleId());
        UserRoleModel update = new UserRoleModel();
        AuthStatus newStatus = AuthStatus.ENABLED.codeOf(userRoleParam.getStatus());
        update.setUpdaterId(userRoleParam.getUpdaterId());
        update.setStatus(newStatus.getCode().byteValue());
        update.setTtl(userRoleParam.getTtl() == null ? null : userRoleParam.getTtl());
        Integer affectRows = userRoleDao.updateByExampleSelective(update, example);
        if(Constants.NO_AFFECT_ROWS.equals(affectRows)) {
            update.setRoleId(userRoleParam.getRoleId());
            update.setUserId(userRoleParam.getUserId());
            update.setCreatorId(userRoleParam.getCreatorId());
            userRoleDao.insert(update);
        }
    }

    public void revokeRole(UserRoleParam userRoleParam) {
        Example example = new Example(UserRoleModel.class);
        example.createCriteria().andEqualTo("userId", userRoleParam.getUserId()).andEqualTo("roleId", userRoleParam.getRoleId());
        UserRoleModel update = new UserRoleModel();
        update.setStatus(AuthStatus.DISABLED.getCode().byteValue());
        update.setUpdaterId(userRoleParam.getUpdaterId());
        userRoleDao.updateByExampleSelective(update, example);
    }

    public List<ThinRole> queryUserRoles(String appId, Long userId) {
        Example userRoleExample = new Example(UserRoleModel.class);
        userRoleExample.createCriteria().andEqualTo("userId", userId);
        List<Long> roleIds = new ArrayList<>();
        Optional.ofNullable(userRoleDao.queryByExample(userRoleExample))
                .ifPresent(userRoleModels -> userRoleModels.forEach(userRoleModel -> roleIds.add(userRoleModel.getRoleId())));

        Example roleExample = new Example(RoleModel.class);
        roleExample.createCriteria().andIn(BaseModel.ID, roleIds)
                .andEqualTo("appId", appId)
                .andEqualTo(BaseModel.LOGIC_DELETE, BooleanEnum.FALSE.getCode())
                .andEqualTo("status", AuthStatus.ENABLED.getCode().byteValue());
        List<ThinRole> thinRoles = queryByExample(roleExample, ThinRole.class);

        return thinRoles;
    }
}