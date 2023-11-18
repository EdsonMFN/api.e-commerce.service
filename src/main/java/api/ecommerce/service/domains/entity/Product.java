package api.ecommerce.service.domains.entity;

import api.ecommerce.service.enums.TypeProduct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumns(value = @PrimaryKeyJoinColumn)
    @Column(name = "id_product",insertable=false, updatable=false)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "price",nullable = false)
    private Double price;

    @Column(name = "description")
    private String description;

    @Column(name = "typeProduct",nullable = false)
    private TypeProduct typeProduct;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_store")
    private Store store;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ItensPayment> itensPayments;

    @Column(name = "qt_item_stock",nullable = false)
    private Integer qtItemStock;

    @Column(name = "discount")
    private int discount;

}