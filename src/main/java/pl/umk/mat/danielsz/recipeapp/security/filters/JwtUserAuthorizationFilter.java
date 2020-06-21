package pl.umk.mat.danielsz.recipeapp.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import pl.umk.mat.danielsz.recipeapp.model.User;
import pl.umk.mat.danielsz.recipeapp.security.SecurityConst;
import pl.umk.mat.danielsz.recipeapp.services.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtUserAuthorizationFilter extends BasicAuthenticationFilter {

    private final SecurityConst securityConst;
    private final UserService userService;

    public JwtUserAuthorizationFilter(AuthenticationManager authenticationManager, SecurityConst securityConst,
                                      UserService userService) {
        super(authenticationManager);
        this.securityConst = securityConst;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String header = request.getHeader(securityConst.getHeader());

        if(header == null || !header.startsWith(securityConst.getTokenPrefix())){
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken auth = getAuth(request);

        SecurityContextHolder.getContext().setAuthentication(auth);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuth(HttpServletRequest req){
        String token = req.getHeader(securityConst.getHeader());
        if(token != null){
            String login = JWT.require(Algorithm.HMAC512(securityConst.getSecret()))
                    .build()
                    .verify(token.replace(securityConst.getTokenPrefix(), ""))
                    .getSubject();

            User user = userService.getByLoginFetchRoles(login);

            if(user != null){
                return new UsernamePasswordAuthenticationToken(user.getLogin(), null, user.getGrantedAuthoritiesRoles());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
