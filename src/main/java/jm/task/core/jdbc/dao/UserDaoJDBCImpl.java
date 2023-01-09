package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;


public class UserDaoJDBCImpl implements UserDao  {
    Connection connection = getConnection();

    public void createUsersTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS " + Util.DB_NAME + ".usertable (\n" +
                    " `id` INT(100) NOT NULL AUTO_INCREMENT,\n" +
                    " `name` VARCHAR(45) NULL,\n" +
                    " `lastName` VARCHAR(45) NULL,\n" +
                    " `age` INT(3) NULL,\n" +
                    "PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;\n");
        } catch (SQLException e) {
            System.err.println("Table not create");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS " + Util.DB_NAME + ".usertable");
        } catch (SQLException e) {
            System.err.println("Table not delete");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement prepState = connection.prepareStatement("INSERT INTO " + Util.DB_NAME + ".usertable (name, lastName, age) VALUES(?,?,?)")) {
            prepState.setString(1, name);
            prepState.setString(2, lastName);
            prepState.setByte(3, age);
            prepState.executeUpdate();
        } catch (SQLException e) {
            System.err.println("User not added");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM " + Util.DB_NAME + ".usertable WHERE id = " + id);
        } catch (SQLException e) {
            System.err.println("User not delete");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        ResultSet res;
        try (Statement stmt = connection.createStatement()) {
            res = stmt.executeQuery("SELECT name, lastName, age FROM " + Util.DB_NAME + ".usertable");
            while (res.next()) {
                userList.add(new User(res.getString("name"), res.getString("lastName"), res.getByte("age")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }


    public void cleanUsersTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM " + Util.DB_NAME + ".usertable");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
