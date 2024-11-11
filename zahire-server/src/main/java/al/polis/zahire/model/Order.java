package al.polis.zahire.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@Data
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private UserCustomer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderNow> orderNowItems;


}
