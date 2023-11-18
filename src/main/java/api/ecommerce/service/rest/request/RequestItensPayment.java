package api.ecommerce.service.rest.request;

import lombok.Data;
@Data
public class RequestItensPayment {

    private Long id;
    private Long idProduct;
    private Integer qtProduct;
    private Integer pricePay;
}
