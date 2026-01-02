package pt.ipp.estg.doa.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.estg.doa.store.model.entity.Payment;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<List<Payment>> findByOrderId(Long orderId);
}
