package com.yipeng.baseservice.authority.service;

import com.yipeng.baseservice.authority.result.ThinRights;
import com.yipeng.baseservice.authority.result.ThinRole;
import com.yipeng.baseservice.authority.result.UserPermissions;
import com.yipeng.framework.core.model.biz.AppInfo;
import com.yipeng.framework.core.model.biz.ContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: yibingzhou
 */
@Service
public class UserPermissionService implements UserDetailsService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RightsService rightsService;

    public UserPermissions queryUserPermissions(String appId, Long userId) {
        List<ThinRole> thinRoles = roleService.queryUserRoles(appId, userId);
        List<ThinRights> thinRights = rightsService.queryUserRights(appId, userId);
        UserPermissions userPermissions = new UserPermissions();
        userPermissions.setUserId(userId);
        userPermissions.setRoles(thinRoles);
        userPermissions.setRights(thinRights);
        return userPermissions;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        AppInfo appInfo = ContextHolder.getAppInfo();
        String appId = appInfo.getAppId();
        UserPermissions permissions = queryUserPermissions(appId, Long.valueOf(userId));

        return null;
    }
}
