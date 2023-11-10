package api.ecommerce.service.domains.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    private Long id;
    private String name;
    private String cpf;
    private Integer age;
    private LocalDate dateOfbirth;
    private String address;
    private String tel;
    private String email;
    private List<ItensPaymentDto> itensPaymentDto;
    private StoreDto storeDto;

    private String msg;

    public ClientDto(String msg) {
        this.msg = msg;
    }
}
