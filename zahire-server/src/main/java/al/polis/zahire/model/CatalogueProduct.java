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

    private String code; // For search, this can be used
    private String description;
    private String shortDescription; // If this is part of your DTO
    private double price;
    private Integer minimumQty;
    private Integer packageSize;
    private double packageWeight;

    @ManyToOne
    @JoinColumn(name = "user_producer_id")
    private UserProducer producer;

    @OneToMany(mappedBy = "catalogueProduct", cascade = CascadeType.ALL)
    private List<OrderRow> orders;

    @OneToMany(mappedBy = "catalogueProduct", cascade = CascadeType.ALL)
    private List<Item> items;

}
