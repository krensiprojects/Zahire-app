package model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class UserProducer extends User{

    @OneToMany(mappedBy = "producer", cascade = CascadeType.ALL)
    private List<CatalogueProduct> catalogProducts;

    // Other fields specific to producers

    // Getters and setters
}
