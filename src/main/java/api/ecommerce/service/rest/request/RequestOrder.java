package api.ecommerce.service.rest.request;

import api.ecommerce.service.domains.entity.Client;
import api.ecommerce.service.domains.entity.Payment;
import api.ecommerce.service.domains.model.ItensPaymentDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RequestOrder {

    private Long id;
    private Client client;
    private Payment payment;
    private List<ItensPaymentDto> itensPayments;
    private LocalDateTime dateOrder;
    private Long idProduct;
    private Integer qtProduct;
}
