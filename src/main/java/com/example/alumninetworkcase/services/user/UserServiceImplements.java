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
        return userRepo.findAll();
    }

    @Override
    public User add(User entity) {
        return userRepo.save(entity);
    }

    @Override
    public User update(User entity) {
        return userRepo.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        userRepo.deleteById(id);
    }

    @Override
    public void delete(User entity) {
        userRepo.delete(entity);
    }

    @Override
    public boolean exists(Integer id) {
        return userRepo.existsById(id);
    }
}
