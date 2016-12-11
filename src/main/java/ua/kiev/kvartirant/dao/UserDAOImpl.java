package ua.kiev.kvartirant.dao;

import org.springframework.beans.factory.annotation.Autowired;
import ua.kiev.kvartirant.model.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserDAOImpl implements UserDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public User findUserByUserName(String username) {
        try {
            Query query = entityManager.createQuery("select u from User u where u.username = :username", User.class);
            query.setParameter("username", username);

            return (User) query.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveUser(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }
}
