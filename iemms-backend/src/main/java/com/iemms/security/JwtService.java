//SHIVAAAAHHH


package com.iemms.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	private final SecretKey secretKey;
	private final long expiration;
	
	public JwtService(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") long expiration) {
		byte[] keyBytes=Decoders.BASE64.decode(secret);
		this.secretKey=Keys.hmacShaKeyFor(keyBytes);
		this.expiration=expiration;
	}
	public String generateToken(String username) {
		Date currentTime=new Date();
		Date expirationTime=new Date(currentTime.getTime()+expiration);
		return Jwts.builder().subject(username).issuedAt(currentTime).expiration(expirationTime).signWith(secretKey).compact();	
		
	}
	public String extractUsername(String token) {
		return extractClaims(token).getSubject();
	}
	public boolean isTokenValid(String token, String username) {

	        Claims claims = extractClaims(token);
	        return claims.getSubject().equals(username)&& claims.getExpiration().after(new Date());
	}

	private Claims extractClaims(String token) {
	        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
	}
}
