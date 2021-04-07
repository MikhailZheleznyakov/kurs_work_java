package com.example.demo.SERVER.tables;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    Order (){};
    public Order(String info){
        this.info = info;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OrderID")
    @SequenceGenerator(name = "OrderID", sequenceName = "order_ID_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "info")
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", info=" + info +
                '}';
    }


}
