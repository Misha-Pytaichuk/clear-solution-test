package clearsolution.pytaichuk.test.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {
    @NotBlank(message = "Field not must be empty")
    private String country;
    @NotBlank(message = "Field not must be empty")
    private String city;
    @NotBlank(message = "Field not must be empty")
    private String street;
    @NotBlank(message = "Field not must be empty")
    private String houseNumber;
}
