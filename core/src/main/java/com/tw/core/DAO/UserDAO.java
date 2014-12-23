package com.tw.core.DAO;

import com.tw.core.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQuery;
import java.util.List;


/**
 * Created by taozhang on 12/19/14.
 */

@Repository
@Transactional
public class UserDAO {
    EntityManagerFactory entityManagerFactory;

    @Autowired
    public UserDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<User> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("select u from User u").getResultList();
    }
}
