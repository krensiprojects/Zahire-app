package model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class UserCustomer extends User{

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;


    // Getters and setters

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
