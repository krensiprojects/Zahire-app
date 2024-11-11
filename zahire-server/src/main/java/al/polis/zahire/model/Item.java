package al.polis.zahire.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int serialNumber;
    private int iotNumber;

    @ManyToOne
    @JoinColumn(name = "order_now_id")
    private OrderNow orderNow;

    @ManyToOne
    @JoinColumn(name = "catalog_product_id")
    private CatalogueProduct catalogProduct;



}
