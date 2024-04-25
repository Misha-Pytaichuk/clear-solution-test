package clearsolution.pytaichuk.test.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Entity
@Table(name = "address_t")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "country - Field not must be empty")
    private String country;
    @NotBlank(message = "city - Field not must be empty")
    private String city;
    @NotBlank(message = "street - Field not must be empty")
    private String street;
    @NotBlank(message = "houseNumber - Field not must be empty")
    private String houseNumber;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
