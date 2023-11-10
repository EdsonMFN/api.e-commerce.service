package api.ecommerce.service.exception.handler;

public class HandlerError extends RuntimeException {
    public HandlerError(String msg) {
        super(msg);
    }
}
