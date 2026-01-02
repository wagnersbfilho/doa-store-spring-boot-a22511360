package pt.ipp.estg.doa.store.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import pt.ipp.estg.doa.store.model.enums.Category;
import pt.ipp.estg.doa.store.model.enums.JewelryType;

import java.math.BigDecimal;

@Data
public class NecklaceDTO extends JewelryDTO {

    public NecklaceDTO(Long id, String name, String material, BigDecimal weight, BigDecimal price, Integer stock,
                       Category category, BigDecimal length) {
        super(id, name, JewelryType.NECKLACE, material, weight, price, stock, category);
        this.length = length;
    }

    @NotNull
    @Positive
    private BigDecimal length;
}
