package pt.ipp.estg.doa.store.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.ipp.estg.doa.store.model.entity.Employee;
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
    public ResponseEntity<List<Employee>> getEmployees() {
        try {
            return ResponseEntity.ok(service.findAll());
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
