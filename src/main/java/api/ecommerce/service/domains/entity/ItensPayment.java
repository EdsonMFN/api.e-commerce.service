package api.ecommerce.service.domains.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "qt_product")
    private Integer qtProduct;

    @Column(name = "subtotal")
    private Double subtotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order")
    private Order order;
}
