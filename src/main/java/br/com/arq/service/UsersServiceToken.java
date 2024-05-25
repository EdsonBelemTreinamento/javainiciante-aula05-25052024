package br.com.arq.service;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class UsersServiceToken {
	
	private static final long EXPIRE_TIME_ONEHOUR=3600000;
	private static final long EXPIRE_TIME_ONEDAY=86400000;
	private static final String SECRET_KEY_BASE64=java.util.Base64.getEncoder().encodeToString("minha-chave-secreta".getBytes());
	private static final Key SECRET_KEY=Keys.secretKeyFor(SignatureAlgorithm.HS512);
	
	 // 7 (dia) * 24 * 60 * 60 * 1000;
	
	public Object getGenerateTokenWithName(String username) {
		Date now = new Date();
		Date expiration = new Date(now.getTime() + EXPIRE_TIME_ONEDAY);
		return Jwts.builder()
				.setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
	  }
	
	public Object getGenerateTokenWithNameAndRole(String username, String role) {
		Date now = new Date();
		Date expiration = new Date(now.getTime() + EXPIRE_TIME_ONEDAY);
		return Jwts.builder()
				.setSubject(username)
				.claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
	  }
	
	
	
	public boolean validateToken(String token) {
		try {
		Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
		 return true;
		}catch(Exception ex ) {
		return false;
		}
	}
	
	public String getUserNameFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody().getSubject();
	}
	
	
	
}
