package api.ecommerce.service.domains.model;

import api.ecommerce.service.enums.DsStatusPayment;
import api.ecommerce.service.enums.TpPayment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

    private Long id;
    private ItensPaymentDto itensPaymentDto;
    private LocalDate payday;
    private TpPayment tpPayment;
    private DsStatusPayment dsStatusPayment;
    private ClientDto client;
    private double payTotal;

    private String msg;
    private Boolean sucess;

    public PaymentDto(String msg) {
        this.msg = msg;
    }
}
