package pt.ipp.estg.doa.store.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pt.ipp.estg.doa.store.model.dto.EmployeeDTO;
import pt.ipp.estg.doa.store.model.dto.EmployeeSalaryDTO;
import pt.ipp.estg.doa.store.model.dto.ManagerDTO;
import pt.ipp.estg.doa.store.model.dto.SalesPersonDTO;
import pt.ipp.estg.doa.store.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService employeeService) {
        this.service = employeeService;
    }

    @GetMapping
    public List<EmployeeDTO> getCustomers() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public EmployeeDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/salesperson")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO createSalesPerson(
            @Valid @RequestBody SalesPersonDTO request) {
        return service.add(request);
    }

    @PostMapping("/manager")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO creatManager(
            @Valid @RequestBody ManagerDTO request) {
        return service.add(request);
    }

    @PutMapping("/{id}/salary")
    public EmployeeDTO updateSalary(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeSalaryDTO request) {
        return service.updateSalary(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/nif/{nif}")
    public EmployeeDTO findByNif(@PathVariable String nif) {
        return service.findByNif(nif);
    }}
