package clearsolution.pytaichuk.test.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    @Email(message = "Incorrect email")
    private String email;
    @NotBlank(message = "Field not must be empty")
    private String firstName;
    @NotBlank(message = "Field not must be empty")
    private String lastName;
    private LocalDate birthDate;
    @Valid
    @NotNull(message = "Address can't be null")
    private AddressDTO address;
    @Pattern(regexp = "^\\+\\d{12}$", message = "Incorrect format. Correct - \"+012345678910\"")
    private String telephoneNumber;
}
