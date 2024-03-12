package com.example.mjs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
public class Client {

    @Id
    @Column(nullable = false, name = "id")
    @Getter
    private String id;
    @Column(nullable = false, name = "pw")
    @Getter
    private String pw;
    @Column(nullable = false, name="name")
    @Getter
    private String name;
    @Column(nullable = false, name = "email")
    @Getter
    private String email;
    @Column(nullable = false,name="gender")
    @Getter
    private String gender;
    @Column(nullable = true, name = "birthday")
    @Getter
    private String birthday;

    public Client(String id, String pw, String name, String email, String gender, String birthday){
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
    }

    public Client() {

    }
}
