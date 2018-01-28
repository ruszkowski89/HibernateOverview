package com.ruszkowski89.Hibernate.dto;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.util.*;

@NamedQuery(name = "User.selectByName", query = "from User where name = :name")
@NamedNativeQuery(name = "User.selectById", query = "SELECT * FROM user WHERE id = :id", resultClass = User.class)
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity (name = "User")
public class User {

    /*    @Transient
    @Temporal(TemporalType.DATE)
    private Date created;

    // single Attribute Override example
    @AttributeOverride(name = "street", column = @Column(name = "HOME_STREET"))
    private Address homeAddress;

    // multiple AttributeOverride inside AttributeOverrides example
    @AttributeOverrides({
        @AttributeOverride(name = "street", column = @Column(name = "OFFICE_STREET")),
        @AttributeOverride(name = "city", column = @Column(name = "OFFICE_CITY")),
        @AttributeOverride(name = "state", column = @Column(name = "OFFICE_STATE")),
        @AttributeOverride(name = "postalCode", column = @Column(name = "OFFICE_POSTALCODE"))})
    private Address officeAddress;*/

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column (name = "USERNAME")
    private String name;
/*    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ADDRESS",
            joinColumns = @JoinColumn(name = "Foreign_key"))
    @GenericGenerator(name = "increment-gen", strategy = "increment")
    @CollectionId(columns = {@Column(name = "ID")}, type = @Type(type = "integer"), generator = "increment-gen")
    private Collection<Address> addressList = new ArrayList<Address>();*/
    @OneToMany(cascade = CascadeType.PERSIST)
    private Collection<Book> booksList = new ArrayList<Book>();

    // CONSTRUCTOR

    public User() {
    }

    // GETTERS & SETTERS


    public Collection<Book> getBooksList() {
        return booksList;
    }

    public void setBooksList(Set<Book> books) {
        this.booksList = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*    public Collection<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(Collection<Address> addressList) {
        this.addressList = addressList;
    }*/

    /*    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Address getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(Address officeAddress) {
        this.officeAddress = officeAddress;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }*/
}
