package pt.ipp.estg.doa.store.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.estg.doa.store.model.dto.CustomerDTO;
import pt.ipp.estg.doa.store.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public List<CustomerDTO> getCustomers() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CustomerDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO create(
            @Valid @RequestBody CustomerDTO request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public CustomerDTO update(
            @PathVariable Long id,
            @Valid @RequestBody CustomerDTO request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/nif/{nif}")
    public CustomerDTO findByNif(@PathVariable String nif) {
        return service.findByNif(nif);
    }

    @GetMapping("/email/{email}")
    public CustomerDTO findByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }
}
