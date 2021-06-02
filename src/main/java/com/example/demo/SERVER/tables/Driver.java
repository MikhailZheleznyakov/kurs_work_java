package com.example.demo.SERVER.tables;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Driver Entity
 */
@Entity
@Getter
@Setter
@Table(name = "drivers")
public class Driver {
    /**
     * initializator
     */
    public Driver(){}

    /**
     * Initializes Cargo and assigns all parameters
     * @param surname
     * @param name
     */
    public Driver(String surname, String name){
        this.surname = surname;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String name;

    /**
     *
     * @return String Driver
     */
    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
