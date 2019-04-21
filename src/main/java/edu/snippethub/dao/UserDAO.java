package edu.snippethub.dao;

import edu.snippethub.entity.User;

import java.sql.SQLException;
import java.util.Collection;

public interface UserDAO {
    void addUser(User user) throws SQLException;

    void updateUser(User user) throws SQLException;

    User getUserById(Long id) throws SQLException;

    User getUserByName(String name) throws SQLException;

    User getUserByEmail(String email) throws SQLException;

    User getUserByEmailOrUsername(String userName, String email) throws SQLException;

    Collection<User> getAllUsers() throws SQLException;

    void deleteUser(User user) throws SQLException;

}
