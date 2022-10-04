package com.example.alumninetworkcase.services.user;

import com.example.alumninetworkcase.exceptions.UserNotFoundException;
import com.example.alumninetworkcase.models.User;
import com.example.alumninetworkcase.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class UserServiceImplements implements UserService{

    private final UserRepo userRepo;

    public UserServiceImplements(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User findById(Integer id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public Collection<User> findAll() {
        return null;
    }

    @Override
    public User add(User entity) {
        return null;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }
}
