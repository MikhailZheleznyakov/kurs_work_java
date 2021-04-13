package com.example.demo.SERVER.tables;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shippings")
public class Shipping {
    public Shipping(){}
    public Shipping(String comment){
        this.comment = comment;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "comment")
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @OneToOne(optional = false)
    @JoinColumn(name = "order_id")
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "shipping")
    private List<Transport> transport;

    public List<Transport> getTransport() {
        return transport;
    }

    public void setTransport(List<Transport> transport) {
        this.transport = transport;
    }

    @Override
    public String toString() {
        return "Shipping{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }
}
