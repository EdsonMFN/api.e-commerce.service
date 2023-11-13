package api.ecommerce.service.domains.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "`order`")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumns(value = {@PrimaryKeyJoinColumn})
    @Column(name = "id_order")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @OneToOne
    @JoinColumn(name = "id_payment")
    private Payment payment;

    @OneToOne
    @JoinColumn(name = "id_deliveryAddress")
    private DeliveryAddress deliveryAddress;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<ItensPayment> itensPayments;

    @Column(name = "dateOrder")
    private LocalDateTime dateOrder;
}
