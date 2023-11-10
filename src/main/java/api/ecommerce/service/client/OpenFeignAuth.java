package api.ecommerce.service.client;

import api.ecommerce.service.domains.model.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth",url = "http://localhost:8090/login")
public interface OpenFeignAuth {

    @GetMapping("/authAuthorization")
    ResponseEntity<UserDto> getAuthorization(@RequestHeader("Authorization") String authorization);
}
