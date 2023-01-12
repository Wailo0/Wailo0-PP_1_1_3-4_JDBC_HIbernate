package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    SessionFactory sessionFactory = getSessionFactory();

    public UserDaoHibernateImpl() {}

    @Override
    public void createUsersTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS " + Util.DB_NAME + ".usertable (\n" +
                " `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                " `name` VARCHAR(45) NULL,\n" +
                " `lastName` VARCHAR(45) NULL,\n" +
                " `age` INT(3) NULL,\n" +
                "PRIMARY KEY(`id`))\n" +
                "ENGINE = InnoDB\n" +
                "DEFAULT CHARACTER SET = utf8;\n";
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createSQLQuery(createTable).executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Table has been create");
    }

    @Override
    public void dropUsersTable() {
        String dropTable = "DROP TABLE IF EXISTS " + Util.DB_NAME + ".usertable";
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createSQLQuery(dropTable).executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Table has been dropped");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        System.out.println("User " + user + "  has been added");
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete User where id = " + id).executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("User with ID - " + id + " deleted");
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User").getResultList();
        session.getTransaction().commit();
        session.close();
        for (User u : users) {
            System.out.println(u);
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete User").executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("User table cleared");
    }
}
