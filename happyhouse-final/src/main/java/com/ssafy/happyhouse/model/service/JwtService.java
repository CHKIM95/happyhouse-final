package com.ssafy.happyhouse.model.service;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ssafy.happyhouse.model.MemberDto;

import io.jsonwebtoken.*;

@Component
public class JwtService {
	
	public static final Logger logger = LoggerFactory.getLogger(JwtService.class);
	
	private String signature = "VUETOKEN";
	private Long expireMin = 5L;

	public String create(MemberDto memberDto) {
		JwtBuilder jwtBuilder = Jwts.builder();
		jwtBuilder.setHeaderParam("typ", "JWT");

		jwtBuilder
			.setSubject("로그인토큰")
			.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expireMin))
			.claim("user", memberDto).claim("greeting", "환영합니다. " + memberDto.getUsername());
		
		jwtBuilder.signWith(SignatureAlgorithm.HS256, signature.getBytes());
		
		String jwt = jwtBuilder.compact();
		logger.info("jwt : {}", jwt);
		return jwt;
	}
	
	public void checkValid(String jwt) {
		Jwts.parser().setSigningKey(signature.getBytes()).parseClaimsJws(jwt);
	}
	
	public Map<String, Object> get(String jwt) {
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser().setSigningKey(signature.getBytes()).parseClaimsJws(jwt);
        } catch (final Exception e) {
            throw new RuntimeException();
        }

        logger.info("claims : {}", claims);
        return claims.getBody();
    }
}
