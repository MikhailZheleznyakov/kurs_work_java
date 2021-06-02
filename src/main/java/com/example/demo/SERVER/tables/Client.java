package com.example.demo.SERVER.tables;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Client entity
 */
@Entity
@Getter
@Setter
@Table(name = "clients",uniqueConstraints = {@UniqueConstraint(columnNames={"login"})})
public class Client {
    /**
     * initializer
     */
    public Client(){};

    /**
     * Initializes Client and assigns all parameters
     * @param surname
     * @param name
     * @param login
     * @param phone
     */
    public Client(String surname, String name, String login, String phone){
        this.surname = surname;
        this.name = name;
        this.login = login;
        this.phone = phone;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String phone;

    /**
     * Converts information to String object
     * @return String Client
     */
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
