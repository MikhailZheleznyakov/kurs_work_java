package com.example.demo.SERVER.tables;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
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

    @Column(nullable = false)
    private Long cost;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departtown")
    private Town departtown;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "arrivaltown")
    private Town arrivaltown;

    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", cost=" + cost +
                '}';
    }
}
