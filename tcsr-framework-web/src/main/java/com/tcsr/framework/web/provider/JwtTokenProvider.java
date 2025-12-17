package com.tcsr.framework.web.provider;

import com.tcsr.framework.common.utils.HttpServletUtils;
import com.tcsr.framework.redis.RedisUtils;
import com.tcsr.framework.web.constant.RedisConstants;
import com.tcsr.framework.web.constant.WebConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.function.Function;

/**
 *
 * @author tangzhong
 * @date   2025-08-28 15:21
 * @since  V1.0.0.0
 */
@Getter
@Setter
@Component
public class JwtTokenProvider {
    
    @Value("${app.jwt-secret:lu-sui-yuan,xing-ze-jiang-zhi;shi-sui-nan,zuo-ze-bi-cheng;tian-dao-chou-qin,da-dao-zhi-jian}")
    private String jwtSecret;
    
    @Value("${app.jwt-expiration-milliseconds:86400000}")
    private long jwtExpirationDate;
    
    public String generateToken(String userId, String username) {
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);
        return Jwts.builder()
                .setId(userId)
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean isBlackToken(String token) {
        return RedisUtils.hasKey(RedisConstants.USER_TOKEN_BLACK + token);
    }

    public String getToken(){
        String bearerToken = HttpServletUtils.getHeader(WebConstants.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(WebConstants.AUTHORIZATION_STARTER)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public Claims resolveToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getJwtClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = this.resolveToken(token);
        return claimsResolver.apply(claims);
    }

}
