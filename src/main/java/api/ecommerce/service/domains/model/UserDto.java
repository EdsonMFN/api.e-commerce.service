package api.ecommerce.service.domains.model;

import api.ecommerce.service.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Long idUsuario;
    private String nomeUsuario;
    private String senha;
    private UserRole role;

    private String msg;
    private Boolean sucess;

    public UserDto(String msg) {
        this.msg = msg;
    }
}
