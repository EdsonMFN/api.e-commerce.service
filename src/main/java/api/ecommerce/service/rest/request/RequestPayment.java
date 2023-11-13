package api.ecommerce.service.rest.request;

import api.ecommerce.service.domains.entity.Client;
import api.ecommerce.service.domains.entity.ItensPayment;
import api.ecommerce.service.domains.entity.Order;
import api.ecommerce.service.enums.DsStatusPayment;
import api.ecommerce.service.enums.TpPayment;
import lombok.Data;

import java.time.LocalDate;
@Data
public class RequestPayment {

    private Long id;
    private ItensPayment itensPayment;
    private LocalDate payday;
    private TpPayment tpPayment;
    private DsStatusPayment dsStatusPayment;
    private Client client;
    private Integer payTotal;
    private Order order;
}
