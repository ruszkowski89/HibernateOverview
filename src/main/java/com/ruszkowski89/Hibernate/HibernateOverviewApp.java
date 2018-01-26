package com.ruszkowski89.Hibernate;

import com.ruszkowski89.Hibernate.dto.Book;
import com.ruszkowski89.Hibernate.dto.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateOverviewApp {
    public static void main (String args[]){

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .configure()
            .build();

        SessionFactory sessionFactory = new MetadataSources(serviceRegistry)
            .buildMetadata()
            .buildSessionFactory();

        // SAVING USER WITH COLLECTION TO DATABASE ---------------------------------

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


        // RETRIEVING USER OBJECT FROM DATABASE -------------------------------------

        /*user = null;

        session = sessionFactory.openSession();
        session.beginTransaction();
        user = session.get(User.class, 1);
        session.close();
        System.out.println(user.getAddressList().size());*/

        // MAPPING ONE TO ONE RELATION -----------------------------------------------

        /*User user = new User();

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
        session2.close();*/

        // DELETING/UPDATING OBJECT --------------------------------------------------

        /*Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = new User();
        user.setName("Grzegorz");
        user.setId(1);

        session.save(user);
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            User retrievedUser = session.get(User.class, 1);
            System.out.println("Username before update: " + retrievedUser.getName());
            retrievedUser.setName("Gregory Drugi");
            System.out.println("New name is: " + retrievedUser.getName());
            session.delete(retrievedUser);
            session.getTransaction().commit();
            System.out.println("This record should not appear in DB: " + retrievedUser.getName());
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("Something bad happened");
        } finally {
            session.close();
        }*/

        // HQL BASICS ------------------------------------------------------------------

        /*Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = new User();
        User user2 = new User();
        user.setName("Wacek");
        user.setId(1);
        user2.setName("Bogdan");
        user2.setId(2);
        session.save(user);
        session.save(user2);
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where id < 10");
        query.setFirstResult(1);
        List<User> list = query.list();
        session.getTransaction().commit();
        session.close();

        for (User someUser : list){
            System.out.println(someUser.getName());
        }*/

        // HQL PARAMETER INJECTION -------------------------------------------------------

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = new User();
        User user2 = new User();
        user.setName("Wacek");
        user.setId(1);
        user2.setName("Bogdan");
        user2.setId(2);
        session.save(user);
        session.save(user2);
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();
        String userInput = "Wacek";
        Query query = session.createQuery("from User where name = :name");
        query.setParameter("name", userInput);

        User newUser = (User) query.getSingleResult();
        session.getTransaction().commit();
        session.close();

        System.out.println(newUser.getName());
    }
}
