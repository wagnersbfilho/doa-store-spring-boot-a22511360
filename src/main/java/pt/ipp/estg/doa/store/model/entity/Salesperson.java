package pt.ipp.estg.doa.store.model.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Salesperson extends Employee {

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("100.0")
    private BigDecimal commissionRate;

    @NotNull
    @PositiveOrZero
    private BigDecimal totalSales;

}
