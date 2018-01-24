package com.ruszkowski89.Hibernate.dto;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

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

    @Id
    private int id;
    @Column (name = "USERNAME")
    private String name;
    @ElementCollection
    @JoinTable(name = "USER_ADDRESS",
            joinColumns = @JoinColumn(name = "Foreign_key"))
    @GenericGenerator(name = "increment-gen", strategy = "increment")
    @CollectionId(columns = {@Column(name = "ID")}, type = @Type(type = "integer"), generator = "increment-gen")
    private Collection<Address> addressList = new ArrayList<Address>();

    // CONSTRUCTOR

    public User() {
    }

    // GETTERS & SETTERS

    public Collection<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(Collection<Address> addressList) {
        this.addressList = addressList;
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
