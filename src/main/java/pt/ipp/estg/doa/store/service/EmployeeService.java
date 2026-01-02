package pt.ipp.estg.doa.store.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pt.ipp.estg.doa.store.exception.DuplicateNifException;
import pt.ipp.estg.doa.store.exception.ResourceNotFoundException;
import pt.ipp.estg.doa.store.model.dto.EmployeeDTO;
import pt.ipp.estg.doa.store.model.dto.EmployeeUpdateDTO;
import pt.ipp.estg.doa.store.model.dto.ManagerDTO;
import pt.ipp.estg.doa.store.model.dto.SalesPersonDTO;
import pt.ipp.estg.doa.store.model.entity.Employee;
import pt.ipp.estg.doa.store.model.entity.Manager;
import pt.ipp.estg.doa.store.model.entity.SalesPerson;
import pt.ipp.estg.doa.store.repository.EmployeeRepository;

@Service
public class EmployeeService extends BasedCrudService<Employee, Long, EmployeeDTO>{

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
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

        if (entity instanceof SalesPerson) {
            SalesPerson sp  = (SalesPerson) entity;
            return new SalesPersonDTO(
                    sp.getId(),
                    sp.getName(),
                    sp.getNif(),
                    sp.getHireDate(),
                    sp.getSalary(),
                    sp.getCommissionRate(),
                    sp.getTotalSales()
            );
        }

        if (entity instanceof Manager) {
            Manager m  = (Manager) entity;
            return new ManagerDTO(
                    m.getId(),
                    m.getName(),
                    m.getNif(),
                    m.getHireDate(),
                    m.getSalary(),
                    m.getDepartment(),
                    m.getBonus());
        }

        return null;
    }

    @Override
    protected Employee toEntity(EmployeeDTO employeeDTO) {

        if (employeeDTO instanceof SalesPersonDTO) {
            SalesPersonDTO dto  = (SalesPersonDTO) employeeDTO;
            SalesPerson salesPerson = new SalesPerson();
            setCommonEmployeeData(salesPerson, dto);
            salesPerson.setCommissionRate(dto.getCommissionRate());
            salesPerson.setTotalSales(dto.getTotalSales());
            return salesPerson;
        }

        if (employeeDTO instanceof ManagerDTO) {
            ManagerDTO dto = (ManagerDTO) employeeDTO;
            Manager manager = new Manager();
            setCommonEmployeeData(manager, dto);
            manager.setBonus(dto.getBonus());
            manager.setDepartment(dto.getDepartment());
            return manager;
        }

        return null;
    }

    @Override
    protected void updateEntity(EmployeeDTO employeeDTO, Employee entity) {

        if (entity instanceof SalesPerson salesPerson) {
            SalesPersonDTO sp = (SalesPersonDTO) employeeDTO;
            setCommonEmployeeData(salesPerson, sp);
            salesPerson.setCommissionRate(sp.getCommissionRate());
            salesPerson.setTotalSales(sp.getTotalSales());
        }

        else if (entity instanceof Manager manager) {
            ManagerDTO dto = (ManagerDTO) employeeDTO;
            setCommonEmployeeData(manager, dto);
            manager.setBonus(dto.getBonus());
            manager.setDepartment(dto.getDepartment());
        }
    }

    private static void setCommonEmployeeData(Employee employee, EmployeeDTO dto) {
        employee.setName(dto.getName());
        employee.setNif(dto.getNif());
        employee.setHireDate(dto.getHireDate());
        employee.setSalary(dto.getSalary());
    }

    @Override
    protected String getEntityClassName() {
        return Employee.class.getSimpleName();
    }
}
