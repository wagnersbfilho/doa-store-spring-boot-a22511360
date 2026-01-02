package pt.ipp.estg.doa.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.estg.doa.store.model.entity.Order;
import pt.ipp.estg.doa.store.model.enums.OrderStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<List<Order>> findByCustomerId(Long customerId);

    Optional<List<Order>> findByStatus(OrderStatus status);
}