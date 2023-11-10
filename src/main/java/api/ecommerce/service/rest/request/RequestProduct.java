package api.ecommerce.service.rest.request;

import api.ecommerce.service.domains.model.StoreDto;
import api.ecommerce.service.enums.TypeProduct;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestProduct {

    private Long id;
    private String name;
    private Integer price;
    private String descriptyon;
    private TypeProduct typeProduct;
    private Integer qtItemStock;
    private int discount;
    private StoreDto store;
}
