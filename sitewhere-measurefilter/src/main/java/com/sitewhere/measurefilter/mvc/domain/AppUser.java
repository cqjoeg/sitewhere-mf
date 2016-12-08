package com.sitewhere.measurefilter.mvc.domain;

import javax.persistence.*;

/**
 * Created by CQ on 2016/11/18.
 *
 * @author Joeg
 */
@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false)
    private long id;

    @Column(name = "username", nullable = false)
    private String username;


    public AppUser() {
    }

    public AppUser(String username) {
        this.username = username;
    }

    public AppUser(long id, String username) {
        this.id = id;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
