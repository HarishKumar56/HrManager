package org.nagarro.hrmanager.service.interfaces;

import org.nagarro.hrmanager.model.User;

public interface UserDaoService {

	void saveUser(User user);

	User getUserByUserName(String User);

}