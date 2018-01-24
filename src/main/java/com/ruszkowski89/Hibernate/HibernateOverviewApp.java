package com.ruszkowski89.Hibernate;

import com.ruszkowski89.Hibernate.dto.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateOverviewApp {
    public static void main (String args[]){

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .configure()
            .build();

        SessionFactory sessionFactory = new MetadataSources(serviceRegistry)
            .buildMetadata()
            .buildSessionFactory();



        // SAVING USER TO DATABASE

        User user = new User();
        user.setId(1);
        user.setName("Michal");

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();



        // RETRIEVING USER OBJECT FROM DATABASE

        user = null;

        session = sessionFactory.openSession();
        session.beginTransaction();
        user = session.get(User.class, 1);
        System.out.println(user.getName());




    }
}
