package api.ecommerce.service.domains.repository;

import api.ecommerce.service.domains.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,Long> {
}
