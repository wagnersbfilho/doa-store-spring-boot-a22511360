package pt.ipp.estg.doa.store.model.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pt.ipp.estg.doa.store.model.dto.OrderDTO;
import pt.ipp.estg.doa.store.model.entity.Order;

@Component
@AllArgsConstructor
public class OrderMapper {

    private final CustomerMapper customerMapper;
    private final PaymentMapper paymentMapper;
    private final OrderItemMapper orderItemMapper;

    public OrderDTO toDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getOrderDate(),
                order.getStatus(),
                order.getCustomer().getId(),
                order.getOrderItems().stream().map(orderItemMapper::toDTO).toList(),
                customerMapper.toDTO(order.getCustomer()),
                order.getPayments().stream().map(paymentMapper::toDTO).toList()
        );
    }
}
