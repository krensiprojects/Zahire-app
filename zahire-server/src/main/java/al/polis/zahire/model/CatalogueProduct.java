package al.polis.zahire.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@Data
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

    @OneToMany(mappedBy = "catalogueProduct", cascade = CascadeType.ALL)
    private List<OrderRow> orders;

    @OneToMany(mappedBy = "catalogueProduct", cascade = CascadeType.ALL)
    private List<Item> items;

}
