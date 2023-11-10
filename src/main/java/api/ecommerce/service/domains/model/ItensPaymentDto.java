package api.ecommerce.service.domains.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItensPaymentDto {

    private Long id;
    private ProductDto product;
    private Integer qtProduct;
    private double pricePay;
}
