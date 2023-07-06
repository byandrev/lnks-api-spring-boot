package com.byandrev.lnks.services;

import com.byandrev.lnks.dao.UserRepository;
import com.byandrev.lnks.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public UserEntity getUserById(Long id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        return userEntityOptional.orElse(null);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        try {
            return userRepository.findByEmail(email).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

}
