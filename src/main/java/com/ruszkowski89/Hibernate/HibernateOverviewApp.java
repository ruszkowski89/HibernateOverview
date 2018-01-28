package com.ruszkowski89.Hibernate;

import com.ruszkowski89.Hibernate.dto.Address;
import com.ruszkowski89.Hibernate.dto.Book;
import com.ruszkowski89.Hibernate.dto.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class HibernateOverviewApp {
    public static void main (String args[]){

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .configure()
            .build();

        SessionFactory sessionFactory = new MetadataSources(serviceRegistry)
            .buildMetadata()
            .buildSessionFactory();

        // ADDING SOME HARDCODED USERS TO DB FOR TESTING PURPOSES

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        for (int i=0; i<10; i++){
            User user = new User();
            user.setName("User " + (i+1));
            user.setId(i+1);
            session.save(user);
        }

        session.getTransaction().commit();
        session.close();

        // SAVING USER WITH COLLECTION TO DATABASE ---------------------------------

        /*Address address1 = new Address();
        address1.setStreet("Marszałkowska");
        Address address2 = new Address();
        address2.setStreet("Wiejska");

        User user = new User();
        user.setId(1);
        user.setName("Michal");
        user.getAddressList().add(address1);
        user.getAddressList().add(address2);

        Session session1 = sessionFactory.openSession();
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
        List<User> list = query.getResultList();
        session.getTransaction().commit();
        session.close();

        for (User someUser : list){
            System.out.println(someUser.getName());
        }*/

        // HQL PARAMETER INJECTION -------------------------------------------------------

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
        String userInput = "Wacek";
        Query query = session.createQuery("from User where name = :name");
        query.setParameter("name", userInput);

        User newUser = (User) query.getSingleResult();
        session.getTransaction().commit();
        session.close();

        System.out.println(newUser.getName());*/

        // NAMED HQL PARAMETERS ----------------------------------------------------

        /*Session session1 = sessionFactory.openSession();
        session1.beginTransaction();

        String userInput = "User 5";
        Query query = session1.getNamedQuery("User.selectByName");
        query.setParameter("name", userInput);

        List<User> list = query.getResultList();
        session1.getTransaction().commit();
        session1.close();

        for (User user : list){
            System.out.println(user.getName());
        }*/

        // NAMED SQL PARAMETER

        Session session1 = sessionFactory.openSession();
        session1.beginTransaction();

        int userInput = 5;
        Query query = session1.getNamedQuery("User.selectById")
                                .setCacheable(true);
        query.setParameter("id", userInput);

        List<User> list = query.getResultList();
        session1.getTransaction().commit();
        session1.close();

        for (User user : list){
            System.out.println(user.getName());
        }

        // CRITERIA API

        /*Session session1 = sessionFactory.openSession();
        session1.beginTransaction();

        CriteriaBuilder criteriaBuilder = session1.getCriteriaBuilder();

        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> user = criteriaQuery.from(User.class);
        criteriaQuery.where(criteriaBuilder.equal(user.get("name"), "User 5"));

        TypedQuery<User> typedQuery = session1.createQuery(criteriaQuery);
        List<User> list = typedQuery.getResultList();

        session1.getTransaction().commit();
        session1.close();

        for (User tempUser : list){
            System.out.println(tempUser.getName() + " " + tempUser.getId());
        }*/

        // SECOND LEVEL CACHE TEST

        /*Session session1 = sessionFactory.openSession();
        session1.beginTransaction();
        User user = session1.get(User.class, 3);
        System.out.println(user.getName());
        session1.getTransaction().commit();
        session1.close();

        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();
        User user2 = session2.get(User.class, 3);
        System.out.println(user.getName());
        session2.getTransaction().commit();
        session2.close();*/

    }
}
