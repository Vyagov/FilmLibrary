package project.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "User with this email is already exist!")
public class EmailAlreadyExistException extends CustomBaseException {
    public EmailAlreadyExistException(String message) {
        super(message);
    }
}
