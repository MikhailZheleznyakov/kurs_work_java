package com.example.demo.SERVER.tables;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Data
@Table(name = "transports")
public class Transport {
    public Transport(){}
    public Transport(String name, Integer capacity, Integer wearout, String transport_type){
        this.name = name;
        this.capacity = capacity;
        this.wearout = wearout;
        this.transport_type = transport_type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private Integer wearout;

    @Column
    private String transport_type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver")
    private Driver driver;

    @Override
    public String toString() {
        return "Transport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", wearout=" + wearout +
                ", transport_type='" + transport_type + '\'' +
                '}';
    }
}
