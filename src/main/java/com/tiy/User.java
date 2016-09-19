package com.tiy;

import javax.persistence.*;

/**
 * Created by DTG2 on 09/15/16.
 */
@Entity
@Table(name = "users") //table name must be different from our class name. Also, user is a special name in Postgres
public class User {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false, unique = true) //constraints regarding the name
    String name;

    @Column(nullable = false)
    String password;

    public User() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}

