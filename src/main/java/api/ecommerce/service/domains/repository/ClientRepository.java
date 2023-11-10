package api.ecommerce.service.domains.repository;

import api.ecommerce.service.domains.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
