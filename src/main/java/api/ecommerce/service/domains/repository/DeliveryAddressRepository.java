package api.ecommerce.service.domains.repository;

import api.ecommerce.service.domains.entity.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress,Long> {
}
