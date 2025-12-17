package com.tcsr.framework.web.utils;

import com.tcsr.framework.common.constant.RoleKeyConstants;
import com.tcsr.framework.web.user.TcsrUserDetail;
import com.tcsr.framework.web.user.UserDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author tangzhong
 * @date   2025-11-03 15:50
 * @since  V1.0.0
 */
public class SecurityUtils {

    private SecurityUtils(){}

    public static Optional<TcsrUserDetail> getAuthentication(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Optional.ofNullable(authentication).map(e->(TcsrUserDetail)e.getPrincipal());
    }

    public static Long getUserId(){
        return getAuthentication().map(TcsrUserDetail::getUserId).orElse(null);
    }

    public static String getUserName(){
        return getAuthentication().map(TcsrUserDetail::getUsername).orElse(null);
    }

    public static List<String> getUserRoles(){
        return getAuthentication().map(TcsrUserDetail::getUser).map(UserDTO::getRoles).orElse(Collections.emptyList());
    }

    public static boolean isAdmin(){
        return getUserRoles().contains(RoleKeyConstants.ROLE_ADMIN);
    }

}