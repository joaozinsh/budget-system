package com.varejonline.budgetsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.varejonline.budgetsystem.model.User;
import com.varejonline.budgetsystem.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Method to fetch all users in the database
	 * @return Object list with all users
	 */
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	/**
	 * Method to fetch a user by ID in the database
	 * @param id
	 * @return Optional with a user
	 */
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}
	
	/**
	 * Method for logging in with a user
	 * @param user
	 * @return Optional with the user logged in or an empty Optional
	 */
	public Optional<User> logIn(User user) {
		List<User> users = userRepository.findAll();
		
		for (User register : users) {
            if (register.equals(user)) {
                user.setLoggedIn(true);
                user.setId(register.getId());
                return Optional.of(userRepository.save(user));
            }
        }
		return Optional.empty();
	}
	
	/**
	 * Method to log out with a user
	 * @param user
	 * @return Optional with the user logged out or an empty option
	 */
	public Optional<User> logOut(User user) {
        List<User> users = userRepository.findAll();
        for (User register : users) {
            if (register.equals(user)) {
                user.setLoggedIn(false);
                user.setId(register.getId());
                return Optional.of(userRepository.save(user));
            }
        }
        return Optional.empty();
    }
}
