package com.vlasenko.subscriptions_example.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Subscription entity
 */

@Entity
@Table(name = "subscriptions")
public class Subscription extends GenericEntity {

    @Column(name = "subscription_name", nullable = false, unique = true)
    private String subscriptionName;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "parameters", nullable = false)
    private HashMap<String, String> parameters = new HashMap<>();

    @ManyToMany(mappedBy = "subscriptions", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

    public Subscription() {
    }

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    public HashMap<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(HashMap<String, String> parameters) {
        this.parameters = parameters != null ? parameters : new HashMap<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}