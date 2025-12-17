package com.tcsr.framework.web.service;

import com.tcsr.framework.web.provider.UserPermissionProvider;
import com.tcsr.framework.web.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 权限服务判断
 * @author tangzhong
 * @date   2025-11-04 15:12
 * @since  V1.0.0
 */
@Component("sps")
@RequiredArgsConstructor
public class SecurityPermissionService {

    private final UserPermissionProvider userPermissionProvider;

    public boolean hasRole(String roleKey) {
        return this.hasAnyRole(roleKey);
    }

    public boolean hasAnyRole(String... roleKeys) {
        return userPermissionProvider.hasAnyRole(SecurityUtils.getUserId(), Arrays.asList(roleKeys));
    }

    public boolean hasPermission(String permission) {
        return hasAnyPermission(permission);
    }

    public boolean hasAnyPermission(String... permissions) {
        return userPermissionProvider.hasAnyPermission(SecurityUtils.getUserId(), Arrays.asList(permissions));
    }

}