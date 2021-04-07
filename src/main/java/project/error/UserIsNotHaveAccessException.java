package project.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User does not have access!")
public class UserIsNotHaveAccessException extends CustomBaseException {
    public UserIsNotHaveAccessException(String msg) {
        super(msg);
    }
}
