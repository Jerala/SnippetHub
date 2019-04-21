package edu.snippethub.dto;

import edu.snippethub.entity.User;
import edu.snippethub.model.UserModel;

public interface UserDTO {

    public User createUserFromUserModel(UserModel userModel) throws NullPointerException;

}
