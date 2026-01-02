package pt.ipp.estg.doa.store.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pt.ipp.estg.doa.store.model.dto.PaymentDTO;
import pt.ipp.estg.doa.store.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @GetMapping
    public List<PaymentDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PaymentDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentDTO create(
            @Valid @RequestBody PaymentDTO request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public PaymentDTO update(
            @PathVariable Long id,
            @Valid @RequestBody PaymentDTO request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/order/{orderId}")
    public List<PaymentDTO> findByOrderId(@PathVariable Long orderId) {
        return service.findByOrderId(orderId);
    }
}
