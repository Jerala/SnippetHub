package edu.snippethub.init;

import edu.snippethub.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserUtil {

    protected List<User> createUsers() {
        return new ArrayList<User>() {{
            add(new User("admin", "$2a$10$RkAt.CfxRpE8NdfhWtMxG.Hkt/U0Qu0EwJfAxegyiwwloFe9Yzxni", true, "jerala", "ADMIN"));
            add(new User("user", "$2a$10$2HBUP/w6/pxhuWj48gnZ5O3Up96RVNpI72EVsynRNb/ieden.tUYe", true, "12345", "USER"));
        }};
    }

}
