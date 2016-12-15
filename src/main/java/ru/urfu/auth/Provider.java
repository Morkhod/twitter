package ru.urfu.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.urfu.entitles.User;
import ru.urfu.model.UserDao;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class Provider implements AuthenticationProvider, UserDetailsService {
    @Autowired
    private UserDao storage;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        User user = storage.getUser(username);

        if (user == null || !user.getLogin().equalsIgnoreCase(username)) {
            throw new BadCredentialsException("Неправильный логин или пароль");
        }

        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Неправильный логин или пароль");
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(user, password, roles);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    public MyUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = storage.getUser(s);
        return new MyUserDetails(user);
    }
}
