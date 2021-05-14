package org.nagarro.hrmanager.repository.interfaces;

import org.nagarro.hrmanager.model.User;

public interface UserDao {

	void saveUser(User user);

	User getUserByUserName(String userName);

}