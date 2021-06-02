package com.example.demo.SERVER.tables;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Order entity
 */
@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
    /**
     * Initializator
     */
    public Order (){}

    /**
     * Initialize Order
     * @param cost
     * @param delivery_type
     */
    public Order(Double cost, String delivery_type){
        this.cost = cost;
        this.delivery_type = delivery_type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Town departtown;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Town arrivaltown;

    @Column
    private String delivery_type;

    @Column
    private Double cost;

    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn
    private Transport transport;

    /**
     * Convert info to String
     * @return String Order
     */
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", cost=" + cost +
                '}';
    }
}
