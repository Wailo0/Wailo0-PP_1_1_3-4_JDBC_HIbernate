package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main extends Util {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl() ;





//        userService.createUsersTable();

//        userService.saveUser("Grisha", "Udin", (byte)32);
//        userService.saveUser("Fedor", "Aravadin", (byte)21);
//        userService.saveUser("Liner", "Pau", (byte)4);
//        userService.saveUser("Luke", "Skywalker", (byte)19);

//        for (User e : userService.getAllUsers()) {
//            System.out.println(e);
//        }
//        userService.removeUserById(2);
//        userService.cleanUsersTable();
//        userService.dropUsersTable();

    }
}
