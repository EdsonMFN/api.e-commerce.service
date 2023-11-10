package api.ecommerce.service.rest.request;

import api.ecommerce.service.domains.entity.Product;
import lombok.Data;
@Data
public class RequestItensPayment {

    private Long id;
    private Product product;
    private Integer qtProduct;
    private Integer pricePay;
}
