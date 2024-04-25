package clearsolution.pytaichuk.test.util.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoundException extends RuntimeException {
    public FoundException(String message) {
        super(message);
    }
}
