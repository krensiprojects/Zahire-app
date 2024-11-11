package model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class OrderNow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToMany(mappedBy = "orderNow", cascade = CascadeType.ALL)
    private List<Item> items;

    // Getters and setters
}
