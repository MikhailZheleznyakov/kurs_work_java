package com.example.demo.SERVER.tables;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "clients",uniqueConstraints = {@UniqueConstraint(columnNames={"login"})})
public class Client {
    public Client(){};
    public Client(String surname, String name, String login, String phone){
        this.surname = surname;
        this.name = name;
        this.login = login;
        this.phone = phone;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ClientID")
    @SequenceGenerator(name = "ClientID", sequenceName = "client_ID_seq", allocationSize = 1)
    @Column
    private Long id;

    @Column
    private String surname;

    @Column
    private String name;

    @Column
    private String login;

    @Column
    private String phone;

    public Long getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private Set<Order> orders;

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

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
