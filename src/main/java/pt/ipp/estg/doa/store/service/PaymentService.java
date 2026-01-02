package pt.ipp.estg.doa.store.service;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pt.ipp.estg.doa.store.exception.ResourceNotFoundException;
import pt.ipp.estg.doa.store.model.dto.PaymentDTO;
import pt.ipp.estg.doa.store.model.entity.Order;
import pt.ipp.estg.doa.store.model.entity.Payment;
import pt.ipp.estg.doa.store.model.mapper.PaymentMapper;
import pt.ipp.estg.doa.store.repository.OrderRepository;
import pt.ipp.estg.doa.store.repository.PaymentRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PaymentService extends BasedCrudService<Payment, Long, PaymentDTO> {

    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final OrderRepository orderRepository;

    public List<PaymentDTO> findByOrderId(Long orderId) {
        List<Payment> result = repository.findByOrderId(orderId).orElse(new ArrayList<>());
        return result.stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    protected JpaRepository<Payment, Long> getRepository() {
        return this.repository;
    }

    @Override
    public PaymentDTO add (PaymentDTO dto) {
        dto.setPaymentDate(LocalDate.now());
        return super.add(dto);
    }

    @Override
    protected PaymentDTO toDTO(Payment payment) {
        return mapper.toDTO(payment);
    }

    @Override
    protected Payment toEntity(PaymentDTO paymentDTO) {
        return mapper.toEntity(paymentDTO, getOrder(paymentDTO));
    }

    @Override
    protected void updateEntity(PaymentDTO dto, Payment payment) {
        mapper.updateEntity(dto, payment, getOrder(dto));
    }

    private Order getOrder(PaymentDTO paymentDTO) {
        return orderRepository.findById(paymentDTO.getIdOrder())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for the Payment: " + paymentDTO.getIdOrder()));
    }

    @Override
    protected String getEntityClassName() {
        return Payment.class.getSimpleName();
    }
}
