package edu.snippethub.init;

import edu.snippethub.entity.User;
import edu.snippethub.service.UserService;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitDataBases implements ApplicationRunner {

    @Autowired
    private UserUtil userUtil;
    @Autowired
    private UserService userService;

    public void run(ApplicationArguments args) {
        try {
            addUsersInDataBase();
        } catch (PSQLException e) {
            e.printStackTrace();
        }
    }

    private void addUsersInDataBase() throws PSQLException {
        List<User> users = userUtil.createUsers();
        for(User user: users)
            userService.save(user);
    }

}
