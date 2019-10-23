package com.practice.SpringSecurityAuthentication.service;

import com.practice.SpringSecurityAuthentication.dao.UserDAO;
import com.practice.SpringSecurityAuthentication.model.User;
import com.practice.SpringSecurityAuthentication.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDAO userDAO;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.getUserByUsername(username);
        System.out.println(user);
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        //here we require to pass UserDetails object

        return new UserDetailsImpl(user);
    }
}
