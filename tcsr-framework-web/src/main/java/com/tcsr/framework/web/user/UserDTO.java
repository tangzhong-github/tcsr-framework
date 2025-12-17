package com.tcsr.framework.web.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 *
 * @author tangzhong
 * @date   2025-11-03 17:25
 * @since  V1.0.0.0
 */
@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long userId;

    private String username;

    @JsonIgnore
    private String password;

    private List<String> roles;

    public static UserDTO of(Long userId, String username){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userId);
        userDTO.setUsername(username);
        return userDTO;
    }

}