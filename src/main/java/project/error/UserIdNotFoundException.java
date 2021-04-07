package project.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "User with this Id is not found!")
public class UserIdNotFoundException extends CustomBaseException {
    public UserIdNotFoundException(String message) {
        super(message);
    }
}
