package com.revature.services;


import com.revature.models.User;
import com.revature.repos.UserRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private UserRepo userRepo;
    private FlightService flightService;
    private Logger log = Logger.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepo userRepo, FlightService flightService) {
        this.userRepo = userRepo;
        this.flightService = flightService;
    }

    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    public List<User> getAll() {
        List<User> users = userRepo.getAll();
        return users;
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public User getByUsername(String username) {
        User user = userRepo.getByUsername(username);
        if (user == null) {
            return null;
        }
        return user;
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public User getById(int user_id) {
        User user = userRepo.getById(user_id);
        return user;
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public User getByCredentials(String username, String password) {
        User user = userRepo.getByCredentials(username, password);
        if (user == null) {
            return null;
        }
        return user;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public User add(User newUser) {
        if (isDuplicateUserName(newUser.getUsername())) {
            return null;
        }
        return userRepo.add(newUser);
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public boolean isDuplicateUserName(String username) {
        if (userRepo.getByUsername(username) == null) {
            return false;
        }
        return true;
    }
}
