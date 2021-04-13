package com.example.demo.SERVER.tables;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rates")
public class Rate {
    public Rate(){};
    public Rate(Long cost){
        this.cost = cost;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "cost", nullable = false)
    private Long cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "rate")
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deliveryType")
    private DeliveryType deliveryType;

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "depart_town")
    private Town depart_town;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_town")
    private Town arrival_town;

    public Town getDepart_town() {
        return depart_town;
    }

    public void setDepart_town(Town depart_town) {
        this.depart_town = depart_town;
    }

    public Town getArrival_town() {
        return arrival_town;
    }

    public void setArrival_town(Town arrival_town) {
        this.arrival_town = arrival_town;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", cost=" + cost +
                '}';
    }
}
