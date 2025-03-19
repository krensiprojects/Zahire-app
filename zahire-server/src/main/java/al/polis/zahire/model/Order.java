package al.polis.zahire.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@NoArgsConstructor
@Data
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    private UserCustomer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderRow> orderRows;


    private LocalDateTime date;
    private boolean shipped; // Indicates if the order has been shipped
    private int quantity;
    private double total;
    private String currency;
    private LocalDateTime shipDate;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private String paymentMethod;
    private LocalDateTime estimatedDelivery;
    private String trackingNumber;
}
