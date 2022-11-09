package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Author Not Found")
public class AuthorNotFoundException extends Exception {

        public AuthorNotFoundException(String errorMessage) {
            super(errorMessage);
        }

}
