package com.example.sda_final.entity;


import javax.persistence.*;
import java.awt.*;
import java.sql.Blob;

@Table(name = "houseTable")
@Entity
public class PublishingHouseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="description")
    private String description;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PublishingHouseEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
