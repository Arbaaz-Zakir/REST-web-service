package com.arbaaz.rest.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	private static int usersCount = 3;
	static {
		users.add(new User(1, "Mark", new Date()));
		users.add(new User(2, "Eva", new Date()));
		users.add(new User(3, "Daryl", new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user) {
		if(user.getId() == null) {
			user.setId(usersCount+1);
		}
		users.add(user);
		return user;
	}
	
	public User findUser(int id) {
		for(User user:users) {
			if(user.getId()==id) {
				return user;
			}
		}
		return null;
	}
	
}
