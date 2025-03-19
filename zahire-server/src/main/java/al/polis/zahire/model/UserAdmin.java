package al.polis.zahire.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class UserAdmin extends User{

    private List<String> permissions; // List of permissions
    private String role; // Role of the admin (e.g., "SuperAdmin", "Manager", etc.)
    private Date lastLogin; // Last login timestamp
    private String department; // Department the admin belongs to (e.g., "HR", "Sales", etc.)
    private Date lockedUntil; // Date until which the account is locked
}
