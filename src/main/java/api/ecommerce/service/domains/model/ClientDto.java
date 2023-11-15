package api.ecommerce.service.domains.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDto implements Serializable {
    @Serial
    private static final long serialVersionUID= 1L;

    private Long id;
    private String name;
    private String cpf;
    private Integer age;
    private LocalDate dateOfbirth;
    private String tel;
    private String email;
    private List<ItensPaymentDto> itensPaymentDto;
    private StoreDto storeDto;

    private String msg;

    public ClientDto(String msg) {
        this.msg = msg;
    }
}
