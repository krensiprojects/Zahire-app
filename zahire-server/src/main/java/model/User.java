package model;


import jakarta.persistence.*;

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

    // Getters, setters, constructors, etc.
}
