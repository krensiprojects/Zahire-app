package model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class CatalogueProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private String description;
    private double price;

    @ManyToOne
    @JoinColumn(name = "user_producer_id")
    private UserProducer producer;

    @OneToMany(mappedBy = "catalogProduct", cascade = CascadeType.ALL)
    private List<OrderNow> orders;

    @OneToMany(mappedBy = "catalogProduct", cascade = CascadeType.ALL)
    private List<Item> items;

    // Getters and setters
}
