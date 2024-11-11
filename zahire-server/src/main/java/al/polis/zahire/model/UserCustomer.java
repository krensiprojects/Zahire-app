package al.polis.zahire.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@Data
@Entity
public class UserCustomer extends User{

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;



}
