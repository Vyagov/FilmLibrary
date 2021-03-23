package project.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "User registration exception!")
public class UserRegistrationException extends CustomBaseException {
    public UserRegistrationException(String msg) {
        super(msg);
    }
}
