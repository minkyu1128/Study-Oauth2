//package com.mk1126sj.oauth.server.auth.service;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ObjectUtils;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//    private final UserService userService;
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = (String) authentication.getPrincipal();
//        String password = (String) authentication.getCredentials();
//        log.error("## => {} | {}", username, password);
//        UserDetails info = userService.loadUserByUsername(username);
//        if (ObjectUtils.isEmpty(info)) {
//            throw new UsernameNotFoundException("user not found");
//        }
//        if (!StringUtils.equals(password, StringUtils.replace(info.getPassword(), "{noop}", ""))) {
//            throw new UsernameNotFoundException("please password check");
//        }
//        return new UsernamePasswordAuthenticationToken(username, password, authentication.getAuthorities());
//    }
//
//}
