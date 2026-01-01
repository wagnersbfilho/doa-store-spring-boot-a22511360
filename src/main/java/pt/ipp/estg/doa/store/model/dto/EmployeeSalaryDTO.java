package pt.ipp.estg.doa.store.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmployeeSalaryDTO {

    @NotNull
    @Positive
    private BigDecimal salary;

}
