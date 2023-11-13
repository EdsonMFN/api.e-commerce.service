package api.ecommerce.service.domains.repository;

import api.ecommerce.service.domains.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}

