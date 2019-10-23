package com.practice.SpringSecurityAuthentication.controller;

import com.practice.SpringSecurityAuthentication.dao.UserDAO;
import com.practice.SpringSecurityAuthentication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    UserDAO userDAO;
    @RequestMapping("/")
    public String indexPage(){
        System.out.println("testing success");
        return "index";
//        return new ModelAndView("index.html");
    }
    @RequestMapping("/register")
    public String registerUser(){
        return "register";
    }
    @RequestMapping(value="/newuser",method = RequestMethod.POST)
    public ModelAndView newUser(@ModelAttribute User user){
        userDAO.saveUser(user);
        return new ModelAndView("redirect:/");
    }
    @RequestMapping("/home")
    public String login(){
        return "home";
    }
}
