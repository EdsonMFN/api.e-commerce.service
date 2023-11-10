package api.ecommerce.service.rest.request;

import api.ecommerce.service.domains.model.ClientDto;
import api.ecommerce.service.domains.model.ProductDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RequestStore {

    private Long id;
    private String name;
    private String cnpj;
    private List<ProductDto> productsDto;
    private List<ClientDto> clientsDto;
}
