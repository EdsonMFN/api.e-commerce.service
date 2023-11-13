package api.ecommerce.service.domains.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Entity
@Table(name = "client")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumns(value = @PrimaryKeyJoinColumn)
    @Column(name = "id_client")
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "cpf",nullable = false,unique = true)
    @CPF
    private String cpf;

    @Column(name = "age",nullable = false)
    private Integer age;

    @Column(name = "date_of_birth",nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "tel",nullable = false,unique = true)
    private String tel;

    @Column(name = "email",nullable = false,unique = true)
    @Email
    private String email;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "id_store")
    private Store store;
}
