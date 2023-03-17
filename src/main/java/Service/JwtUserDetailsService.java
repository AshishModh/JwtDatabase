package Service;

import Model.UserInfo;
import Repository.UserDAO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;


public class JwtUserDetailsService implements UserDetailsService {


    private UserDAO userDAO;

    public JwtUserDetailsService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userDAO.getUserInfo(username);
        GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getRole());
        UserDetails userDetails = (UserDetails) new User(userInfo.getUsername(), userInfo.getPassword(), Arrays.asList(authority));
        System.out.println(userDetails);
        return userDetails;
    }
}
