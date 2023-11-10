package api.ecommerce.service.domains.entity;

import api.ecommerce.service.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "login")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_acesso")
    @PrimaryKeyJoinColumns(value = {@PrimaryKeyJoinColumn})
    private Long id;

    @Column(name = "nome_usuario",nullable = false,unique = true)
    private String nomeUsuario;

    @Column(name = "senha_usuario",nullable = false,unique = true)
    private String senha;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
