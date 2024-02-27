package online.shop.online_shop.security;

import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springdoc.api.ErrorMessage;
import  com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@CrossOrigin
public class JwtFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailsService;
    public String sessionToken;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            sessionToken = token;
            try {
                String phoneNumberFromToken = jwtProvider.getPhoneNumberFromToken(token);
                UserDetails userDetails =  userDetailsService.loadUserByUsername(phoneNumberFromToken);
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                userDetails, userDetails.getPassword(), userDetails.getAuthorities()));
            } catch (ExpiredJwtException e) {
                response.setStatus(401);
                response.setContentType("application/json");
                ErrorMessage jwt_expired = new ErrorMessage("Jwt expired");
                logger.error(jwt_expired.getMessage() + "  " + jwt_expired.getId());
                new ObjectMapper().writeValue(response.getWriter(), jwt_expired);
                return ;
            } catch (SignatureException e) {
                response.setStatus(435);
                response.setContentType("application/json");
                ErrorMessage jwt_expired = new ErrorMessage("Jwt invalid");
                logger.error(jwt_expired.getMessage() + "  " + jwt_expired.getId());
                new ObjectMapper().writeValue(response.getWriter(), jwt_expired);
                return;
            } catch (Exception e) {
                response.setStatus(400);
                ErrorMessage jwt_expired = new ErrorMessage(e.getMessage());
                logger.error(jwt_expired.getMessage() + "  " + jwt_expired.getId());
                new ObjectMapper().writeValue(response.getWriter(), jwt_expired);
                return;
            }
        }
        doFilter(request, response, filterChain);
    }
}
