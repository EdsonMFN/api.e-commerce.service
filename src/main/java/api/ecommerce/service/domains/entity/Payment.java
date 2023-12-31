package api.ecommerce.service.domains.entity;

import api.ecommerce.service.enums.DsStatusPayment;
import api.ecommerce.service.enums.TpPayment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class  Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumns(value = @PrimaryKeyJoinColumn)
    @Column(name = "id_payment")
    private Long id;

    @Column(name = "dt_pay_day",nullable = false)
    private LocalDate payday;

    @Enumerated(EnumType.STRING)
    @Column(name = "tp_payment",nullable = false)
    private TpPayment tpPayment;

    @Enumerated(EnumType.STRING)
    @Column(name = "ds_status_payment",nullable = false)
    private DsStatusPayment dsStatusPayment;

    @Column(name = "pay_total",nullable = false)
    private Double payTotal;
}
