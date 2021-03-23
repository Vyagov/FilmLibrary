package project.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Username already exist")
public class UsernameAlreadyExistException extends  CustomBaseException{
    public UsernameAlreadyExistException(String msg) {
        super(msg);
    }
}
