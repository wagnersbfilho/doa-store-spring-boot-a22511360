package pt.ipp.estg.doa.store.model.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.ipp.estg.doa.store.model.enums.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment implements pt.ipp.estg.doa.store.model.entity.Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private LocalDate paymentDate = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
