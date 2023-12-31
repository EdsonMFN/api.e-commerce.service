package api.ecommerce.service.domains.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StoreDto {

    private Long id;
    private String name;
    private String cnpj;
    private List<ProductDto> productsDto;
    private List<ClientDto> clientsDto;

    private String msg;
    private Boolean sucess;

    public StoreDto(String msg) {
        this.msg = msg;
    }
}
