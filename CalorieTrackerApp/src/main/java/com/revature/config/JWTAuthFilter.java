//package com.revature.config;
//
//import io.jsonwebtoken.io.IOException;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.SneakyThrows;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//
///*
// * JWTAuthFilter is responsible for processing and validating JWT tokens for authentication
// * and setting the authenticated user in the SecurityContext for subsequent request handling.
// */
//
//@Component
//public class JWTAuthFilter extends OncePerRequestFilter {
//    @Autowired
//    private JWTUtils jwtUtils;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @SneakyThrows // Lombok annotation to handle checked exceptions without a try-catch block
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        // Retrieves the Authorization header from the request
//        final String authHeader = request.getHeader("Authorization");
//        final String jwtToken; //  Storing the JWT token
//        final String userName; // Storing the username
//
//        // Checks if the Authorization header is missing or blank
//        if (authHeader == null || authHeader.isBlank()){
//            filterChain.doFilter(request, response);
//            return ;
//        }
//
//        jwtToken = authHeader.substring(7); // Extracts the JWT token from the header (skipping "Bearer ")
//        userName = jwtUtils.extractUsername(jwtToken); // Extracts the username from the JWT token
//
//        // Checks if the username is not null and the security context does not have an authentication object
//        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
//            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
//
//            // Validates the JWT token
//            if (jwtUtils.isTokenValid(jwtToken, userDetails)) {
//                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
//                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities()
//                );
//                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                securityContext.setAuthentication(token);
//                SecurityContextHolder.setContext(securityContext);
//            }
//        }
//        filterChain.doFilter(request, response);
//
//    }
//}
