package pt.ipp.estg.doa.store.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pt.ipp.estg.doa.store.model.dto.*;
import pt.ipp.estg.doa.store.service.JewelryService;

import java.util.List;

@RestController
@RequestMapping("/api/jewelry")
@AllArgsConstructor
public class JewelryController {

    private final JewelryService service;

    @GetMapping
    public List<JewelryDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public JewelryDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/type/{type}")
    public List<JewelryDTO> findByType(@PathVariable String type) {
        return service.findByType(type);
    }

    @GetMapping("/category/{category}")
    public List<JewelryDTO> findByCategory(@PathVariable String category) {
        return service.findByCategory(category);
    }

    @GetMapping("/in-stock")
    public List<JewelryDTO> findInStock() {
        return service.findInStock();
    }

    @PostMapping("/earring")
    @ResponseStatus(HttpStatus.CREATED)
    public JewelryDTO createEarring(
            @Valid @RequestBody EarringDTO request) {
        return service.add(request);
    }

    @PostMapping("/necklace")
    @ResponseStatus(HttpStatus.CREATED)
    public JewelryDTO createNecklacen(
            @Valid @RequestBody NecklaceDTO request) {
        return service.add(request);
    }

    @PostMapping("/ring")
    @ResponseStatus(HttpStatus.CREATED)
    public JewelryDTO createRing(
            @Valid @RequestBody RingDTO request) {
        return service.add(request);
    }

    @PutMapping("/{id}/price")
    public JewelryDTO updatePrice(
            @PathVariable Long id,
            @Valid @RequestBody JewelryUpdateDTO request) {
        return service.updatePrice(id, request);
    }

    @PutMapping("/{id}/stock")
    public JewelryDTO updatStock(
            @PathVariable Long id,
            @Valid @RequestBody JewelryUpdateDTO request) {
        return service.updateStock(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
