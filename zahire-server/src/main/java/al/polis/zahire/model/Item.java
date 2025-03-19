package al.polis.zahire.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int serialNumber;
    private int lotNumber;
    private Date expirationDate; // Expiration date (useful for perishable items)
    private double unitPrice; // Price per unit
    private String unitType; // Type of unit, e.g., "kg", "box", "liter"


    @ManyToOne
    private OrderRow orderRow;

    @ManyToOne
    private CatalogueProduct catalogueProduct;



}
