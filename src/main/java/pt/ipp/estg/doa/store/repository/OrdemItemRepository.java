package pt.ipp.estg.doa.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.estg.doa.store.model.entity.OrderItem;

@Repository
public interface OrdemItemRepository extends JpaRepository<OrderItem, Long> {
}
