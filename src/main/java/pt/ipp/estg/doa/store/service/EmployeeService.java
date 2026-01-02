package pt.ipp.estg.doa.store.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pt.ipp.estg.doa.store.exception.DuplicateNifException;
import pt.ipp.estg.doa.store.exception.ResourceNotFoundException;
import pt.ipp.estg.doa.store.model.dto.EmployeeDTO;
import pt.ipp.estg.doa.store.model.dto.EmployeeUpdateDTO;
import pt.ipp.estg.doa.store.model.entity.Employee;
import pt.ipp.estg.doa.store.model.mapper.EmployeerMapper;
import pt.ipp.estg.doa.store.repository.EmployeeRepository;

@Service
public class EmployeeService extends BasedCrudService<Employee, Long, EmployeeDTO>{

    private final EmployeeRepository repository;
    private final EmployeerMapper mapper;

    public EmployeeService(EmployeeRepository repository, EmployeerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public EmployeeDTO findByNif(String nif) {
        Employee employee = repository.findByNif(nif)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for NIF: " + nif));;
        return toDTO(employee);
    }

    public EmployeeDTO updateSalary(Long id, EmployeeUpdateDTO dto) {
        Employee entity = getRepository().findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot update. " + getEntityClassName() + " not found. ID: " + id));
        entity.setSalary(dto.getSalary());
        return toDTO(entity);
    }

    @Override
    public EmployeeDTO add(EmployeeDTO dto) {
        if (repository.existsByNif(dto.getNif())) {
            throw new DuplicateNifException(
                    "Employee with NIF " + dto.getNif() + " already exists");
        }
        return super.add(dto);
    }

    @Override
    protected JpaRepository<Employee, Long> getRepository() {
        return this.repository;
    }

    @Override
    protected EmployeeDTO toDTO(Employee entity) {
        return mapper.toDTO(entity);
    }

    @Override
    protected Employee toEntity(EmployeeDTO employeeDTO) {
        return mapper.toEntity(employeeDTO);
    }

    @Override
    protected void updateEntity(EmployeeDTO employeeDTO, Employee entity) {
       mapper.updateEntity(employeeDTO, entity);
    }

    @Override
    protected String getEntityClassName() {
        return Employee.class.getSimpleName();
    }
}
