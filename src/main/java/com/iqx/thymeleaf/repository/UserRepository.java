package com.iqx.thymeleaf.repository;

import com.iqx.thymeleaf.domain.User;

import java.util.List;

/**
 * User Repo Interface
 */
public interface UserRepository {

    //Save or Update User
    User saveOrUpdateUser(User user);

    //Delete User
    void deleteUser(Long id);

    //Find User By ID
    User getUserById(Long id);

    //Find all Users
    List<User> listUsers();

    /**
     * Get all the user
     * @return
     */
    List<User> listUser();
}