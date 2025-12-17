package com.tcsr.framework.web.provider;

import com.tcsr.framework.web.user.UserDTO;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author tangzhong
 * @date   2025-11-03 10:44
 * @since  V1.0.0.0
 */
public interface TcsrUserInfoProvider {

    Optional<UserDTO> getByUsername(String username);

    List<String> getDataScopes(Long userId);

    boolean hasAnyRole(Long userId, List<String>  roles);

    boolean hasAnyPermission(Long userId, List<String> permissions);

}
