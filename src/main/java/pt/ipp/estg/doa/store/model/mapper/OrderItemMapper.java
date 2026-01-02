package pt.ipp.estg.doa.store.model.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pt.ipp.estg.doa.store.model.dto.OrderItemDTO;
import pt.ipp.estg.doa.store.model.entity.Jewelry;
import pt.ipp.estg.doa.store.model.entity.Order;
import pt.ipp.estg.doa.store.model.entity.OrderItem;

@Component
@AllArgsConstructor
public class OrderItemMapper {

    private final JewelryMapper jewelryMapper;

    public OrderItemDTO toDTO(OrderItem orderItem) {
        return new OrderItemDTO(
                orderItem.getId(),
                orderItem.getOrder().getId(),
                orderItem.getQuantity(),
                orderItem.getPriceAtPurchase(),
                orderItem.getJewelry().getId(),
                jewelryMapper.toDTO(orderItem.getJewelry())
        );
    }

    public OrderItem toEntity(OrderItemDTO dto, Order order, Jewelry jewelry) {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(dto.getQuantity());
        orderItem.setPriceAtPurchase(dto.getPriceAtPurchase());
        orderItem.setOrder(order);
        orderItem.setJewelry(jewelry);
        return orderItem;
    }

}
