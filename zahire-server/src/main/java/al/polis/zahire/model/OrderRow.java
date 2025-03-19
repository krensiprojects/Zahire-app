package al.polis.zahire.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@Data
@Entity
public class OrderRow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    private CatalogueProduct catalogueProduct;

    @ManyToOne
    private Order order;

    @OneToMany(mappedBy = "orderRow", cascade = CascadeType.ALL)
    private List<Item> items;


}
