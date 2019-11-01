package pers.jason.example.rest.service;

import pers.jason.example.entity.User;

/**
 * @author 姜治昊
 */
public interface UserService {

  User findUserByUsername(String username);

  User findByUsernameOrPhoneNumber(String s);

}
