package pt.ipp.estg.doa.store.model.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesPerson extends Employee {

    private BigDecimal commissionRate;
    private BigDecimal totalSales;

}
