package api.ecommerce.service.domains.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {
    private Long id;
    private ClientDto client;
    private PaymentDto payment;
    private DeliveryAddressDto deliveryAddress;
    private List<ItensPaymentDto> itensPayments;
    private LocalDateTime dateOrder;

    private String msg;

    public OrderDto(String msg) {
        this.msg = msg;
    }
}
