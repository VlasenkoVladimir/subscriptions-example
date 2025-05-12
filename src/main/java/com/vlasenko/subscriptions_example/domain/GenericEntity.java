package com.vlasenko.subscriptions_example.domain;

import jakarta.persistence.*;

/**
 * Generic entity
 */

@MappedSuperclass
public class GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public GenericEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}