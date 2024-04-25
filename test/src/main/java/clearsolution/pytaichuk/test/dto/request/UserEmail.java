package clearsolution.pytaichuk.test.dto.request;

import jakarta.validation.constraints.Email;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEmail {
    @Email(message = "Incorrect email")
    private String email;
}
