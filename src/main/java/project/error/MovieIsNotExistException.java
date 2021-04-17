package project.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Movie is not exist!")
public class MovieIsNotExistException extends CustomBaseException {
    public MovieIsNotExistException(String msg) {
        super(msg);
    }
}
