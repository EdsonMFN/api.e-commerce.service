package api.ecommerce.service.domains.repository;

import api.ecommerce.service.domains.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
