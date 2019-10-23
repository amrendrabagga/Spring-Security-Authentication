package com.practice.SpringSecurityAuthentication.handler;

import com.practice.SpringSecurityAuthentication.model.User;
import com.practice.SpringSecurityAuthentication.model.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
//import org.springframework.security.core.userdetails.User;


public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
//        System.out.println("CALLING FROM CUSTOMAUTHENTICATION SUCCESS HANDLER");
//       //we can create sessios
//        HttpSession session = request.getSession();
//        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        session.setAttribute("username", authUser.getUsername());
//        session.setAttribute("authorities", authentication.getAuthorities());
//
//        //set our response to OK status
//        response.setStatus(HttpServletResponse.SC_OK);
//
//        //since we have created our custom success handler, its up to us to where
//        //we will redirect the user after successfully login
//        //httpServletResponse.sendRedirect("home");
//        chain.doFilter(request,response);
//    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        System.out.println("CALLING FROM CUSTOMAUTHENTICATION SUCCESS HANDLER");
        //we can create sessios
        HttpSession session = httpServletRequest.getSession();
        UserDetailsImpl authUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setAttribute("username", authUser.getUsername());
        session.setAttribute("authorities", authentication.getAuthorities());

        //set our response to OK status
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        //since we have created our custom success handler, its up to us to where
        //we will redirect the user after successfully login
        //httpServletResponse.sendRedirect("home");
//        chain.doFilter(request,response);
        httpServletResponse.sendRedirect("home");

    }
}
