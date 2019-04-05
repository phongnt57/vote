package com.pnt.app.vote.service;


import com.pnt.app.vote.model.User;
import com.pnt.app.vote.model.UserDto;

import java.util.List;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);

    User findById(Long id);
}
