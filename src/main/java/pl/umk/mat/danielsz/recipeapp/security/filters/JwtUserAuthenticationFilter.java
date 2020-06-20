package pl.umk.mat.danielsz.recipeapp.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.umk.mat.danielsz.recipeapp.model.dto.UserLoginDto;
import pl.umk.mat.danielsz.recipeapp.security.SecurityConst;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JwtUserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final SecurityConst securityConst;

    public JwtUserAuthenticationFilter(AuthenticationManager authenticationManager, SecurityConst securityConst){
        this.authenticationManager = authenticationManager;
        this.securityConst = securityConst;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            UserLoginDto userLoginDto = new ObjectMapper().readValue(request.getInputStream(), UserLoginDto.class);

            return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    userLoginDto.getLogin(),
                    userLoginDto.getPassword()
                )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        String token = JWT.create()
                .withSubject(authResult.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + securityConst.getExpirationTime()))
                .sign(Algorithm.HMAC512(securityConst.getSecret().getBytes()));

        response.addHeader(securityConst.getHeader(), securityConst.getTokenPrefix());
    }
}
