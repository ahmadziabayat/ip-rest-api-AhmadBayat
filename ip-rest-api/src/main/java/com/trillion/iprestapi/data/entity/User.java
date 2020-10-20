package com.trillion.iprestapi.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="USER")
public class User implements Serializable {


    @Id
    @Column(name="USER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="LAST_NAME")
    private String lastName;

    @Column(name="EMAIL_ADDRESS")
    private String email;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name="CREATED_BY_USER", referencedColumnName = "USER_ID")
    private Set<IpAddress> created_user_id;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name="UPDATED_BY_USER", referencedColumnName = "USER_ID")
    private Set<IpAddress> updated_User_id;

    public User() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<IpAddress> getCreated_user_id() {
        return created_user_id;
    }

    public void setCreated_user_id(Set<IpAddress> created_user_id) {
        this.created_user_id = created_user_id;
    }

    public Set<IpAddress> getUpdated_User_id() {
        return updated_User_id;
    }

    public void setUpdated_User_id(Set<IpAddress> updated_User_id) {
        this.updated_User_id = updated_User_id;
    }

    @Override
    public String toString() {
        return "User{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
