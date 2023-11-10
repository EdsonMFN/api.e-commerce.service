package api.ecommerce.service.domains.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "itens_payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItensPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumns(value = @PrimaryKeyJoinColumn)
    @Column(name = "id_Itens_payment")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product")
    private Product product;

    @OneToMany(mappedBy = "itensPayment", cascade = CascadeType.ALL)
    private List<Payment> payment;

    @Column(name = "qt_product")
    private Integer qtProduct;

    @Column(name = "price_pay")
    private double pricePay;

}
