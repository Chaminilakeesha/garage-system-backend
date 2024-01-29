package com.example.garagesystembackend.filters;

import com.example.garagesystembackend.DTO.responses.ExtractTokenResponseDTO;
import com.example.garagesystembackend.services.UserDetailsServiceImpl;
import com.example.garagesystembackend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ExtractTokenResponseDTO extractTokenResponseDTO = extractTokenFromRequest(request);
        String jwtToken = extractTokenResponseDTO.getToken();
        String email = extractTokenResponseDTO.getEmail();

        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            if(jwtUtils.isTokenValid(jwtToken,userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }

    public ExtractTokenResponseDTO extractTokenFromRequest(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");
        ExtractTokenResponseDTO extractTokenResponseDTO = new ExtractTokenResponseDTO();

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
            extractTokenResponseDTO.setToken(authorizationHeader.substring(7));
            extractTokenResponseDTO.setEmail(jwtUtils.extractEmail(extractTokenResponseDTO.getToken()));
        }

        return extractTokenResponseDTO;
    }
}