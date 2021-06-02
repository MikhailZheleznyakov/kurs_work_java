package com.example.demo.SERVER.tables;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Cargo entity
 */
@Entity
@Getter
@Setter
@Table(name = "cargoes")
public class Cargo {
    /**
     * initializer
     */
    public Cargo(){}

    /**
     * Initializes Cargo and assigns all parameters
     * @param name
     * @param weight
     */
    public Cargo(String name, Integer weight){
        this.name = name;
        this.weight = weight;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer weight;

    @ManyToOne(fetch = FetchType.EAGER)
    private Order order;

    /**
     * Converts information to String object
     * @return String Cargo
     */
    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
