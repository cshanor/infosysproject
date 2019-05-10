package com.revature.repos;

import com.revature.models.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserRepo {

    private SessionFactory factory;
    Logger log = Logger.getLogger(UserRepo.class);

    @Autowired
    public UserRepo(SessionFactory factory) {
        this.factory = factory;
    }

    public List<User> getAll() {
        Session session = factory.getCurrentSession();
        return session.createQuery("from Users", User.class).getResultList();
    }

    public User getByCredentials(String username, String password) {
        Session session = factory.getCurrentSession();

        User user = null;

        Query userQuery = session.createQuery("from Users where user.username = :usrnm", User.class);
        userQuery.setParameter("usrnm", username);

        try {
            user = (User) userQuery.getSingleResult();
        } catch (NoResultException nre) {
            log.info("No user found with these credentials: username: " + username + " pw: " + password);
        } catch (Exception e) {
            log.info("Exception thrown in getByCredentials when invoked with these credentials: username: " + username + " pw: " + password);
        }

        return user;
    }

    public User getByUsername(String username) {
        User user = new User();
        Session session = factory.getCurrentSession();
        Query userQuery = session.createQuery("from Users user where user.username = :usrnm", User.class);
        userQuery.setParameter("usrnm", username);
        try {
            log.info("Before getByUsername query. ");
            user = (User) userQuery.getSingleResult();
            if (user == null) {
                log.info("No user found with given username: " + username);
                return null;
            }
            log.info("After query: --------");
        } catch (NoResultException nre) {
            log.info("No user found with these credentials: username: " + username);
            return new User();
        } catch (Exception e) {
            log.info("Exception thrown in getByUsername when invoked with username: " + username);
            return new User();
        }
        return user;
    }

    public User getById(int user_id) {
        Session session = factory.getCurrentSession();
        return session.get(User.class, user_id);
    }

    public User add(User newUser) {
        Session session = factory.getCurrentSession();
        session.save(newUser);
        return newUser;
    }
}
