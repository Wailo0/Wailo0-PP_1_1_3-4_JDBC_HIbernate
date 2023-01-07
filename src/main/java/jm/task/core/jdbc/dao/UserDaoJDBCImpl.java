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
        String createTable = "CREATE TABLE IF NOT EXISTS " + Util.DB_NAME + ".usertable (\n" +
            " `id` INT(100) NOT NULL AUTO_INCREMENT,\n" +
            " `name` VARCHAR(45) NULL,\n" +
            " `lastName` VARCHAR(45) NULL,\n" +
            " `age` INT(3) NULL,\n" +
            "PRIMARY KEY (`id`))\n" +
            "ENGINE = InnoDB\n" +
            "DEFAULT CHARACTER SET = utf8;\n";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTable);
        } catch (SQLException e) {
            System.err.println("Table not create");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String dropTable = "DROP TABLE IF EXISTS " + Util.DB_NAME + ".usertable";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(dropTable);
        } catch (SQLException e) {
            System.err.println("Table not delete");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String svUser = "INSERT INTO " + Util.DB_NAME + ".usertable (name, lastName, age) VALUES(?,?,?)";
        try (PreparedStatement prepState = connection.prepareStatement(svUser)) {
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
        String removeUser = "DELETE FROM " + Util.DB_NAME + ".usertable WHERE id = " + id;
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(removeUser);
        } catch (SQLException e) {
            System.err.println("User not delete");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String getAll = "SELECT name, lastName, age FROM " + Util.DB_NAME + ".usertable";
        ResultSet res;
        try (Statement stmt = connection.createStatement()) {
            res = stmt.executeQuery(getAll);
            while (res.next()) {
                userList.add(new User(res.getString("name"), res.getString("lastName"), res.getByte("age")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }


    public void cleanUsersTable() {
        String deleteTable = "DELETE FROM " + Util.DB_NAME + ".usertable";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(deleteTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
