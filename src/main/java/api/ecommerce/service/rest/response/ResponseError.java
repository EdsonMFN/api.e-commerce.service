package api.ecommerce.service.rest.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
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
