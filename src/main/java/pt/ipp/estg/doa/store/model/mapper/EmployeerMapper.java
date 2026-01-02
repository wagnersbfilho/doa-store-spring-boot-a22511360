package pt.ipp.estg.doa.store.model.mapper;

import org.springframework.stereotype.Component;
import pt.ipp.estg.doa.store.model.dto.EmployeeDTO;
import pt.ipp.estg.doa.store.model.dto.ManagerDTO;
import pt.ipp.estg.doa.store.model.dto.SalesPersonDTO;
import pt.ipp.estg.doa.store.model.entity.Employee;
import pt.ipp.estg.doa.store.model.entity.Manager;
import pt.ipp.estg.doa.store.model.entity.SalesPerson;

@Component
public class EmployeerMapper {

    public EmployeeDTO toDTO(Employee entity) {

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

    public Employee toEntity(EmployeeDTO employeeDTO) {

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

    public void updateEntity(EmployeeDTO employeeDTO, Employee entity) {

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
}
