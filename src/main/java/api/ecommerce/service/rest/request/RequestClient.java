package api.ecommerce.service.rest.request;

import api.ecommerce.service.domains.model.ProductDto;
import api.ecommerce.service.domains.model.StoreDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class RequestClient {

    private Long id;
    private String name;
    private String cpf;
    private Integer age;
    private LocalDate dateOfBirth;
    private String address;
    private String tel;
    private String email;
    private List<ProductDto> productDto;
    private StoreDto storeDto;
}
