package org.nagarro.hrmanager.service.interfaces;

public interface JwtTokenService {

	String generateToken(String userName);

}