package al.polis.zahire.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@Data
@Entity
public class UserProducer extends User{

    @OneToMany(mappedBy = "producer", cascade = CascadeType.ALL)
    private List<CatalogueProduct> catalogProducts;


}
