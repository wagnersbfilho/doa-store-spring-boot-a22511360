package pt.ipp.estg.doa.store.model.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class JewelryUpdateDTO {

    @Positive
    private BigDecimal price;

    @PositiveOrZero
    private Integer stock;

}
