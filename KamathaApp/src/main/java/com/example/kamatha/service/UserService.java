package com.example.kamatha.service;

import com.example.kamatha.model.User;
/**
 *  2020-11-10
 * @author SHAN
 *
 */
public interface UserService {
  
 public User findUserByEmail(String email);
 
 public User findUserByFirstname(String firstname);
 
 public User findUserById(long id);
 
 public void saveUser(User user);
 
 

}