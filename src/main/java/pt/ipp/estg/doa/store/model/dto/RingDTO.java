package pt.ipp.estg.doa.store.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pt.ipp.estg.doa.store.model.enums.Category;
import pt.ipp.estg.doa.store.model.enums.JewelryType;

import java.math.BigDecimal;

@Data
public class RingDTO extends JewelryDTO {

    public RingDTO(Long id, String name, String material, BigDecimal weight, BigDecimal price, Integer stock,
                   Category category, Integer size) {
        super(id, name, JewelryType.NECKLACE, material, weight, price, stock, category);
        this.size = size;
    }

    @NotNull
    @Min(10)
    @Max(30)
    private Integer size;
}
