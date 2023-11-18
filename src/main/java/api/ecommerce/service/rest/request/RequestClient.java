package api.ecommerce.service.rest.request;

import api.ecommerce.service.domains.entity.DeliveryAddress;
import api.ecommerce.service.domains.model.ProductDto;
import api.ecommerce.service.domains.model.StoreDto;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class RequestClient implements Serializable {

    @Serial
    private static final long serialVersionUID= 1L;

    private Long id;
    private String name;
    private String cpf;
    private Integer age;
    private DeliveryAddress deliveryAddress;
    private LocalDate dateOfBirth;
    private String tel;
    private String email;
    private List<ProductDto> productDto;
    private StoreDto storeDto;
}
