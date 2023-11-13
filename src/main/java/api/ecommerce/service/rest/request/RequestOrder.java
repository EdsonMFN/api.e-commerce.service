package api.ecommerce.service.rest.request;

import api.ecommerce.service.domains.entity.Client;
import api.ecommerce.service.domains.entity.DeliveryAddress;
import api.ecommerce.service.domains.entity.ItensPayment;
import api.ecommerce.service.domains.entity.Payment;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RequestOrder {

    private Long id;
    private Client client;
    private Payment payment;
    private DeliveryAddress deliveryAddress;
    private List<ItensPayment> itensPayments;
    private LocalDateTime dateOrder;
}
