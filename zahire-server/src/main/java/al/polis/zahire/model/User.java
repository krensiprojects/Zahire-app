package al.polis.zahire.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@MappedSuperclass
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private boolean enabled;
    private boolean active;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
