package api.ecommerce.service.domains.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "deliveryAddress")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_deliveryAddress")
    @PrimaryKeyJoinColumns(value = @PrimaryKeyJoinColumn)
    private Long id;

    @Column(name = "address",nullable = false)
    private String address;

    @Column(name = "number",nullable = false)
    private int number;

    @Column(name = "state",nullable = false)
    private String state;

    @Column(name = "district",nullable = false)
    private String district;
}
