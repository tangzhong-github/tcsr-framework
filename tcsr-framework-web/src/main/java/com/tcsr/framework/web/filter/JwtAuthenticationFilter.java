package com.tcsr.framework.web.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcsr.framework.common.api.Response;
import com.tcsr.framework.common.utils.MessageUtils;
import com.tcsr.framework.web.user.TcsrUserDetail;
import com.tcsr.framework.web.user.UserDTO;
import com.tcsr.framework.web.provider.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

/**
 * JWT认证过滤器
 * @author tangzhong
 * @date   2025-11-03 15:50
 * @since  V1.0.0
 */
@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.getToken();
        if (StringUtils.hasText(token)) {
            if(!this.validateToken(token, response)){
                return;
            }
            String userId = jwtTokenProvider.getJwtClaim(token, Claims::getId);
            String username = jwtTokenProvider.getJwtClaim(token, Claims::getSubject);
            TcsrUserDetail userDetails = new TcsrUserDetail(UserDTO.of(Long.parseLong(userId), username));
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    public boolean validateToken(String token, HttpServletResponse response) throws IOException{
        Boolean isValid = Boolean.FALSE;
        try {
            //解析Token，若能解析成功，则说明Token有效
            jwtTokenProvider.resolveToken(token);
            //检查Token是否在Redis黑名单中
            if(jwtTokenProvider.isBlackToken(token)){
                log.error("Token[{}]已登出！", token);
                this.writeError(response, MessageUtils.getMessage("framework.mybatis.security.token.logout"));
            }else{
                isValid = Boolean.TRUE;
            }
        } catch (ExpiredJwtException e) {
            log.error("Token 已过期：", e);
            this.writeError(response, MessageUtils.getMessage("framework.mybatis.security.token.expired"));
        } catch (Exception e) {
            log.error("Token 校验失败：", e);
            this.writeError(response, MessageUtils.getMessage("framework.mybatis.security.token.invalid"));
        }
        return isValid;
    }

    private void writeError(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(Response.fail(HttpStatus.UNAUTHORIZED.value(), message)));
    }

}
