package org.nagarro.hrmanager.service;

import javax.transaction.Transactional;

import org.nagarro.hrmanager.model.User;
import org.nagarro.hrmanager.repository.interfaces.UserDao;
import org.nagarro.hrmanager.service.interfaces.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userDaoService")
public class UserDaoServiceImpl implements UserDaoService {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	/* (non-Javadoc)
	 * @see org.nagarro.flight.service.UserDaoService#saveUser(org.nagarro.flight.model.User)
	 */
	@Override
	@Transactional
	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDao.saveUser(user);
		
	}
	
	/* (non-Javadoc)
	 * @see org.nagarro.flight.service.UserDaoService#getUserByUserName(java.lang.String)
	 */
	@Override
	@Transactional
	public User getUserByUserName(String userName) {
		return userDao.getUserByUserName(userName);
	}

}
