package com.tcsr.framework.web.service.impl;

import com.tcsr.framework.web.provider.UserInfoProvider;
import com.tcsr.framework.web.user.TcsrUserDetail;
import com.tcsr.framework.web.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 
 * @author tangzhong
 * @date   2025-11-04 15:12
 * @since  V1.0.0
 */
@Service
@RequiredArgsConstructor
public class TcsrUserDetailsService implements UserDetailsService {
    
    private final UserInfoProvider userInfoProvider;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = userInfoProvider.getByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new TcsrUserDetail(user);
    }

}
