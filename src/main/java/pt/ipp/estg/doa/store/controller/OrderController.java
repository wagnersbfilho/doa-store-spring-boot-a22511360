package pt.ipp.estg.doa.store.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pt.ipp.estg.doa.store.model.dto.OrderDTO;
import pt.ipp.estg.doa.store.model.dto.OrderUpdateDTO;
import pt.ipp.estg.doa.store.model.enums.OrderStatus;
import pt.ipp.estg.doa.store.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService service;

    @GetMapping
    public List<OrderDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public OrderDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO create(
            @Valid @RequestBody OrderDTO request) {
        return service.add(request);
    }

    @PutMapping("/{id}/status")
    public OrderDTO updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody OrderUpdateDTO request) {
        return service.updateStatus(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        OrderUpdateDTO dto = new OrderUpdateDTO(OrderStatus.CANCELED.name());
        service.updateStatus(id, dto);
    }

    @GetMapping("/customer/{customerId}")
    public List<OrderDTO> findByCustomerId(@PathVariable Long customerId) {
        return service.findByCustomerId(customerId);
    }

    @GetMapping("/status/{status}")
    public List<OrderDTO> findByStatus(@PathVariable String status) {
        return service.findByStatus(status);
    }
}
