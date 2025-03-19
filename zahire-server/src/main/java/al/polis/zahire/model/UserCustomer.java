package al.polis.zahire.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@NoArgsConstructor
@Data
@Entity
public class UserCustomer extends User{

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;

    private String paymentMethod;
    private boolean newsletterSubscribed;
    private LocalDateTime lastShipment;
    private String shipmentAddress1;
    private String shipmentAddress2;
    private String shipmentCity;
    private String shipmentCountry;
    private String postalCode;
}
