package com.tcsr.framework.web.utils;

import com.tcsr.framework.common.utils.SpringUtils;
import com.tcsr.framework.web.user.TcsrUserDetail;
import com.tcsr.framework.web.constant.WebConstants;
import com.tcsr.framework.web.provider.TcsrUserInfoProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author tangzhong
 * @since  2025-11-03 15:50
 */
public class SecurityUtils {

    private SecurityUtils(){}

    private static TcsrUserInfoProvider userInfoProvider;

    private static TcsrUserInfoProvider userInfoProvider(){
        if(userInfoProvider == null){
            userInfoProvider = SpringUtils.getBean(TcsrUserInfoProvider.class);
        }
        return userInfoProvider;
    }

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

    public static boolean isAdmin(){
        return hasAnyRole(WebConstants.ROLE_ADMIN);
    }

    public static boolean hasAnyRole(String...role){
        return userInfoProvider().hasAnyRole(getUserId(), Arrays.asList(role));
    }

    public static boolean hasAnyPermission(String...permission){
        return userInfoProvider().hasAnyPermission(getUserId(), Arrays.asList(permission));
    }

}