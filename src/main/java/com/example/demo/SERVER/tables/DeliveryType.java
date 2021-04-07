package com.example.demo.SERVER.tables;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "deliverytypes")
public class DeliveryType {
    public DeliveryType(){}
    public DeliveryType(String typeName, String description){
        this.typeName = typeName;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "typeName", nullable = false)
    private String typeName;

    @Column(name = "description")
    private String description;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "DeliveryType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
