package com.tcsr.framework.web.service;

import com.tcsr.framework.web.utils.SecurityUtils;
import org.springframework.stereotype.Component;

/**
 * @author tangzhong
 * @since  2025-11-04 15:12
 */
@Component("ss")
public class SecurityService {

    public boolean hasRole(String role) {
        return hasAnyRole(role);
    }

    public boolean hasAnyRole(String... roles) {
        return SecurityUtils.hasAnyRole(roles);
    }

    public boolean hasPermission(String permission) {
        return hasAnyPermission(permission);
    }

    public boolean hasAnyPermission(String... permissions) {
        return SecurityUtils.hasAnyPermission(permissions);
    }

}