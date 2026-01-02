package pt.ipp.estg.doa.store.service;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pt.ipp.estg.doa.store.exception.OutOfStockException;
import pt.ipp.estg.doa.store.exception.ResourceNotFoundException;
import pt.ipp.estg.doa.store.model.dto.OrderDTO;
import pt.ipp.estg.doa.store.model.dto.OrderItemDTO;
import pt.ipp.estg.doa.store.model.dto.OrderUpdateDTO;
import pt.ipp.estg.doa.store.model.entity.Customer;
import pt.ipp.estg.doa.store.model.entity.Jewelry;
import pt.ipp.estg.doa.store.model.entity.Order;
import pt.ipp.estg.doa.store.model.enums.OrderStatus;
import pt.ipp.estg.doa.store.model.mapper.OrderItemMapper;
import pt.ipp.estg.doa.store.model.mapper.OrderMapper;
import pt.ipp.estg.doa.store.repository.CustomerRepository;
import pt.ipp.estg.doa.store.repository.JewelryRepository;
import pt.ipp.estg.doa.store.repository.OrderRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService extends BasedCrudService<Order, Long, OrderDTO> {

    private final OrderRepository repository;
    private final JewelryRepository jewelryRepository;
    private final CustomerRepository customerRepository;

    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    public List<OrderDTO> findByCustomerId(Long customerId) {
        List<Order> result = repository.findByCustomerId(customerId).orElse(new ArrayList<>());
        return result.stream()
                .map(orderMapper::toDTO)
                .toList();
    }

    public List<OrderDTO> findByStatus(String status) {
        try {
            OrderStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Order Status " + status + " not found");
        }

        List<Order> result = repository.findByStatus(OrderStatus.valueOf(status.toUpperCase()))
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for Status: " + status));
        return result.stream()
                .map(this::toDTO)
                .toList();
    }

    public OrderDTO updateStatus(Long id, OrderUpdateDTO dto) {
        String status = dto.getStatus();
        Order entity = getRepository().findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot update. " + getEntityClassName() + " not found. ID: " + id));
        try {
            OrderStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Order Status " + status + " not found");
        }
        entity.setStatus(OrderStatus.valueOf(status.toUpperCase()));
        return toDTO(entity);
    }

    @Override
    protected JpaRepository<Order, Long> getRepository() {
        return this.repository;
    }

    @Override
    protected OrderDTO toDTO(Order entity) {
        return orderMapper.toDTO(entity);
    }

    @Override
    protected Order toEntity(OrderDTO dto) {
        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setStatus(OrderStatus.PENDING);
        order.setCustomer(getCustomer(dto.getIdCustomer()));
        order.setOrderItems(dto.getOrderItems().stream().map(
                orderItemDTO -> {
                    return orderItemMapper.toEntity(orderItemDTO, order, getJewelry(orderItemDTO));
                }).toList());
        return order;
    }

    @Override
    protected void updateEntity(OrderDTO dto, Order order) {
    }

    private Customer getCustomer(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for the Order: " + customerId));
    }

    private Jewelry getJewelry(OrderItemDTO dto) {
        Jewelry jewelry = jewelryRepository.findById(dto.getIdJewelry())
                .orElseThrow(() -> new ResourceNotFoundException("Jewelry not found for the Order: " + dto.getIdJewelry()));
        if (jewelry.getStock() == 0)
            throw new OutOfStockException("Jewelry Stock Not Enough. ID: " + dto.getIdJewelry());
        return jewelry;
    }

    @Override
    protected String getEntityClassName() {
        return Order.class.getSimpleName();
    }
}
