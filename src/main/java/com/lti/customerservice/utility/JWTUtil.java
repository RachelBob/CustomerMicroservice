package com.lti.customerservice.utility;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	
	@Value("${jwt.secretkey}")
	private String jwtSecret;

	@Value("${jwt.token.validity}")
	private long tokenValidity;		//5 mins
	
	@Value("${jwt.claim.sub}")
	private String claimSub;

	public String generateToken() {
		Claims claims = Jwts.claims().setSubject(claimSub);
		long nowMillis = System.currentTimeMillis();
		long expMillis = nowMillis + tokenValidity;
		Date exp = new Date(expMillis);
		return Jwts.builder().setClaims(claims).setIssuedAt(new Date(nowMillis)).setExpiration(exp)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}
}
