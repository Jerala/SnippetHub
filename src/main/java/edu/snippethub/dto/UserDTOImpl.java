package edu.snippethub.dto;

import edu.snippethub.entity.User;
import edu.snippethub.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDTOImpl implements UserDTO {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User createUserFromUserModel(UserModel userModel) throws NullPointerException {
        User user = new User();
        user.setUserName(userModel.getUserName());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setEmail(userModel.getEmail());
        user.setRole("USER");
        user.setEnabled(true);
        return user;
    }

}
