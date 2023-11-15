package api.ecommerce.service.rest.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ResponseError implements Serializable {

    @Serial
    private static final long serialVersionUID= 1L;

    private LocalDateTime timestamp;
    private String msg;
    private String error;
    private HttpStatus status;

    public ResponseError(String msg, String error, HttpStatus status) {
        this();
        this.msg = msg;
        this.error = error;
        this.status = status;
    }
    public ResponseError() {
        timestamp = LocalDateTime.now();
    }
}
