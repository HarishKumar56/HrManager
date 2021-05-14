package org.nagarro.hrmanager.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.nagarro.hrmanager.service.interfaces.JwtTokenService;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service("jwtTokenService")
public class JwtTokenServiceImpl implements JwtTokenService {
	
	private final String SECRET_KEY = "secret";
	
	/* (non-Javadoc)
	 * @see org.nagarro.hrmanager.service.JwtTokenService#generateToken(java.lang.String)
	 */
	@Override
	public String generateToken(String userName) {
		Map<String , Object> claims = new HashMap<String , Object>();
		return createToken(claims , userName);
	}

	private String createToken(Map<String, Object> claims, String userName) {
		
		return Jwts
				.builder()
				.setClaims(claims)
				.setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
				.compact();
	}

}
