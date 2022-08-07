package com.example.onlab25.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FillerCustom extends OncePerRequestFilter {
    @Autowired
    private UserDetailsServiceCustom userDetailsServiceCustom;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Lay thong tin usename(email) trong session
        String userEmail = (String) request.getSession().getAttribute("My_Session");

        //Tao doi tuong xac thuc
        UsernamePasswordAuthenticationToken authentication = getAuthentication(userEmail);
        if(authentication == null){
            filterChain.doFilter(request, response);
            return;
        }

        // luu vao trong context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String email){
        if(email == null){

            return null;
        }
        // lay ra thong tin cua user theo email
        UserDetails user = userDetailsServiceCustom.loadUserByUsername(email);

        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }
}
