package com.ruszkowski89.Hibernate;

import com.ruszkowski89.Hibernate.dto.Book;
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

        // SAVING USER WITH COLLECTION TO DATABASE

        /*Address address = new Address();
        address.setCity("Warsaw");
        address.setStreet("Marszałkowska");
        address.setState("Mazowieckie");
        address.setPostalCode("46-114");

        Address address2 = new Address();
        address2.setCity("Kraków");
        address2.setStreet("Poniatowskiego");
        address2.setState("Malopolskie");
        address2.setPostalCode("55-123");

        User user = new User();
        user.setId(1);
        user.setName("Michal");
        user.getAddressList().add(address);
        user.getAddressList().add(address2);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();*/


        // RETRIEVING USER OBJECT FROM DATABASE

        /*user = null;

        session = sessionFactory.openSession();
        session.beginTransaction();
        user = session.get(User.class, 1);
        session.close();
        System.out.println(user.getAddressList().size());*/

        // MAPPING ONE TO ONE RELATION

        User user = new User();

        Book book = new Book();
        book.setTitle("Tańczący z wilkami");

        Book book2 = new Book();
        book2.setTitle("1984");

        user.setName("Jacek");
        user.getBooksList().add(book);
        user.getBooksList().add(book2);

        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();
        session2.persist(user);
        session2.getTransaction().commit();
        session2.close();

    }
}
