package com.tw.core.DAO;

import com.tw.core.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by taozhang on 12/19/14.
 */

@Repository
@Transactional
public class UserDAO {
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    @Autowired
    public UserDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Transactional
    public List<User> findAll() {
        return entityManager.createQuery("select u from User u").getResultList();
    }

    @Transactional
    public User findByPrimaryId(long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public User findByEmail(String email) {
        String sql = "select u from User u where u.email = :name" ;
        Query query = entityManager.createQuery(sql);
        query.setParameter("name", email);
        return (User) query.getSingleResult();
    }

    @Transactional
    public void createUser(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Transactional
    public void updateUser(User user) {
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    @Transactional
    public void deleteUserByID(long id) {
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }
}
