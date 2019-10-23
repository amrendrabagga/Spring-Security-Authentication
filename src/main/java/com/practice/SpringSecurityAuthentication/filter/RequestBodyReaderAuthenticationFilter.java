package com.practice.SpringSecurityAuthentication.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.SpringSecurityAuthentication.model.User;
import org.apache.commons.io.IOUtils;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.RequestDispatcher.ERROR_MESSAGE;

public class RequestBodyReaderAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public RequestBodyReaderAuthenticationFilter() {
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        System.out.println("CALLING FROM UsernamePasswordAuthenticationFilter");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            UsernamePasswordAuthenticationToken token
                    = new UsernamePasswordAuthenticationToken(username, password);

            // Allow subclasses to set the "details" property
            setDetails(request, token);

            return this.getAuthenticationManager().authenticate(token);

    }
}
