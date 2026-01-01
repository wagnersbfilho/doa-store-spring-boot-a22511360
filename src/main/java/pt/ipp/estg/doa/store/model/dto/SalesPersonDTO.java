package pt.ipp.estg.doa.store.model.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import pt.ipp.estg.doa.store.model.enums.EmployeeType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SalesPersonDTO extends EmployeeDTO {

    public SalesPersonDTO(Long id, String name, String nif, LocalDate hireDate, BigDecimal salary,
                          BigDecimal commissionRate, BigDecimal totalSales) {
        super(EmployeeType.SALESPERSON, id, name, nif, hireDate, salary);
        this.commissionRate = commissionRate;
        this.totalSales = totalSales;
    }

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("100.0")
    private BigDecimal commissionRate;

    @NotNull
    @PositiveOrZero
    private BigDecimal totalSales;

}
