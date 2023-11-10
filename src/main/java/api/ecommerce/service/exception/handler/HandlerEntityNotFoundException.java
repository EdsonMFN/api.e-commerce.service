package api.ecommerce.service.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HandlerEntityNotFoundException extends RuntimeException{
    public HandlerEntityNotFoundException(String msg) {
        super(msg);
    }
}
