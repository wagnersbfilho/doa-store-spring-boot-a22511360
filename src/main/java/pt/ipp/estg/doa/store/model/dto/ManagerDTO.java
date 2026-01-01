package pt.ipp.estg.doa.store.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import pt.ipp.estg.doa.store.model.enums.EmployeeType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ManagerDTO extends EmployeeDTO {

    public ManagerDTO(Long id, String name, String nif, LocalDate hireDate, BigDecimal salary,
                      String department, BigDecimal bonus) {
        super(EmployeeType.MANAGER, id, name, nif, hireDate, salary);
        this.department = department;
        this.bonus = bonus;
    }

    @NotBlank
    private String department;

    @NotNull
    @PositiveOrZero
    private BigDecimal bonus;
}
