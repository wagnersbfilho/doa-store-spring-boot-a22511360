package pt.ipp.estg.doa.store.service;

import org.springframework.stereotype.Service;
import pt.ipp.estg.doa.store.model.entity.Employee;
import pt.ipp.estg.doa.store.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }
}
