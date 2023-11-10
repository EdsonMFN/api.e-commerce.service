package api.ecommerce.service.domains.model;

import api.ecommerce.service.enums.TypeProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private double price;
    private String descriptyon;
    private TypeProduct typeProduct;
    private Integer qtItemStock;
    private int discount;
    private StoreDto store;

    private String msg;
    private Boolean sucess;

    public ProductDto(String msg) {
        this.msg = msg;
    }
}
