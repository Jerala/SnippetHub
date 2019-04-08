package edu.snippethub.service;

import edu.snippethub.dao.UserDAO;
import edu.snippethub.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Arrays;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userDAO.getUserByName(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (user == null) {
            throw new UsernameNotFoundException("Invalid name or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_" + user.getRole())));
    }

    public void save(User user) {
        try {
            userDAO.addUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }

}
