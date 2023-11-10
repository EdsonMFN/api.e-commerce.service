package api.ecommerce.service.domains.repository;

import api.ecommerce.service.domains.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
