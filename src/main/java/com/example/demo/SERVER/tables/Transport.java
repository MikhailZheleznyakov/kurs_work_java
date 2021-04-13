package com.example.demo.SERVER.tables;

import javax.persistence.*;

@Entity
@Table(name = "transports")
public class Transport {
    public Transport(){}
    public Transport(String name, Long capacity, Float wearout){
        this.name = name;
        this.capacity = capacity;
        this.wearout = wearout;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "capacity", nullable = false)
    private Long capacity;

    @Column(name = "wearout", nullable = false)
    private Float wearout;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Float getWearout() {
        return wearout;
    }

    public void setWearout(Float wearout) {
        this.wearout = wearout;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver")
    private Driver driver;

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private TransportType transportType;

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Shipping shipping;

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", wearout=" + wearout +
                '}';
    }
}
