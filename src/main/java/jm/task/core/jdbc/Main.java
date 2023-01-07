package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main extends Util {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl() ;

//        us.createUsersTable();
//        us.dropUsersTable();
//        us.saveUser("Grisha", "Udin", (byte)32);
//        us.saveUser("Fedor", "Aravadin", (byte)21);
//        us.saveUser("Liner", "Pau", (byte)8);
//        us.removeUserById(1);

//        for (User e : us.getAllUsers()) {
//            System.out.println(e);
//        }

//        us.cleanUsersTable();


    }
}
