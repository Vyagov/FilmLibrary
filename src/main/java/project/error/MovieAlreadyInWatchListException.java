package project.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "The movie is already on the list!")
public class MovieAlreadyInWatchListException extends CustomBaseException {
    public MovieAlreadyInWatchListException(String message) {
        super(message);
    }
}
