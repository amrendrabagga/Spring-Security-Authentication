package com.practice.SpringSecurityAuthentication.dao;

import com.practice.SpringSecurityAuthentication.model.User;
import com.practice.SpringSecurityAuthentication.model.UserRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    public void saveUser(User user){
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        user.setUserRole(new UserRole(102));//for user role
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        session.save(user);
        tr.commit();
        session.close();
    }
    public User getUserByUsername(String username){
        Session session = sessionFactory.openSession();
        User user = session.get(User.class,username);
        return user;
    }
}
