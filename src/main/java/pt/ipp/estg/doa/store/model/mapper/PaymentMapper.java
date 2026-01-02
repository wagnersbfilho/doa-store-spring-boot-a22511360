package pt.ipp.estg.doa.store.model.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pt.ipp.estg.doa.store.model.dto.PaymentDTO;
import pt.ipp.estg.doa.store.model.entity.Order;
import pt.ipp.estg.doa.store.model.entity.Payment;

@Component
@AllArgsConstructor
public class PaymentMapper {

    public PaymentDTO toDTO(Payment payment) {
        return new PaymentDTO(
                payment.getId(),
                payment.getAmount(),
                payment.getPaymentDate(),
                payment.getPaymentMethod(),
                payment.getOrder().getId()
        );
    }

    public Payment toEntity(PaymentDTO dto, Order order) {
        Payment payment = new Payment();
        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setOrder(order);
        return payment;
    }

    public void updateEntity(PaymentDTO dto, Payment payment, Order order) {
        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setOrder(order);
    }
}
