package com.byandrev.lnks.services;

import com.byandrev.lnks.entities.UserEntity;

public interface UserService {

    void createUser(UserEntity user);

    UserEntity getUserById(Long id);

    UserEntity getUserByEmail(String email);

}
