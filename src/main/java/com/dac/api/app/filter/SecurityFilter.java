package com.dac.api.app.filter;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dac.api.app.model.user.User;
import com.dac.api.app.providers.JWTProvider;
import com.dac.api.app.repository.user.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {

            String header = request.getHeader("Authorization");

            if (header != null) {
                var subjectToken = this.jwtProvider.validateToken(header);

                if (subjectToken.isEmpty()) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Token inválido ou expirado");
                    return;
                }

                Optional<User> user = this.userRepository.findById(Long.valueOf(subjectToken));

                if (user.isPresent()) {
                    User authUser = user.get();
                    request.setAttribute("user_id", subjectToken);
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(subjectToken,
                            null,
                            authUser.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
