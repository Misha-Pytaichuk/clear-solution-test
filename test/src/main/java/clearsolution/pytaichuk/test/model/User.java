package clearsolution.pytaichuk.test.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;

@Entity
@Table(name = "user_t")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Email(message = "Incorrect email")
    @NotBlank(message = "email - Field not must be empty")
    private String email;
    @NotBlank(message = "firstName - Field not must be empty")
    private String firstName;
    @NotBlank(message = "lastName - Field not must be empty")
    private String lastName;
    private LocalDate birthDate;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;
    @Pattern(regexp = "^\\+\\d{12}$", message = "Incorrect format. Correct - \"+012345678910\"")
    private String telephoneNumber;
}
