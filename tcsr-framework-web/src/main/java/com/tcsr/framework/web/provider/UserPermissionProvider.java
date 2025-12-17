package com.tcsr.framework.web.provider;

import java.util.List;

/**
 *
 * @author tangzhong
 * @date   2025-11-03 10:44
 * @since  V1.0.0
 */
public interface UserPermissionProvider {

    List<String> getDataScopes(Long userId);

    boolean hasAnyRole(Long userId, List<String> roleKeys);

    boolean hasAnyPermission(Long userId, List<String> permissions);

}
