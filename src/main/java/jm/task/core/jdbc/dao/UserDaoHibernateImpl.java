package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    SessionFactory sessionFactory = getSessionFactory();

    UserDaoJDBCImpl daoJDBC = new UserDaoJDBCImpl();


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        daoJDBC.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        daoJDBC.dropUsersTable();
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
