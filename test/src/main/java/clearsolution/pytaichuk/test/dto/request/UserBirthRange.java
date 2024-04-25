package clearsolution.pytaichuk.test.dto.request;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBirthRange {
    private LocalDate from;
    private LocalDate to;
}
