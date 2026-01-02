package pt.ipp.estg.doa.store.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import pt.ipp.estg.doa.store.model.enums.Category;
import pt.ipp.estg.doa.store.model.enums.JewelryType;

import java.math.BigDecimal;

@Data
public class EarringDTO extends JewelryDTO {

    public EarringDTO(Long id, String name, String material, BigDecimal weight, BigDecimal price, Integer stock,
                      Category category, String claspType) {
        super(id, name, JewelryType.EARRING, material, weight, price, stock, category);
        this.claspType = claspType;
    }

    @NotBlank
    private String claspType;

}
