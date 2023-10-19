@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(unique = true, nullable = false)
    private String emailAddress;

    @OneToOne(mappedBy = "user")
    private UserProfile userProfile;

    // Add a relationship mapping to represent the "User can have many Flowers" relationship.
    @OneToMany(mappedBy = "user")
    private List<Flower> flowers;

    

    // Other user-related attributes and getters/setters
}

