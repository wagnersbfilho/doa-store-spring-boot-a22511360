package pt.ipp.estg.doa.store.model.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.ipp.estg.doa.store.model.enums.Category;
import pt.ipp.estg.doa.store.model.enums.JewelryType;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JewelryDTO {

    private Long id;

    @NotBlank
    @Size(max = 200)
    private String name;

    @NotNull
    private JewelryType type;

    @NotBlank
    private String material;

    @NotNull
    @Positive
    private BigDecimal weight;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    @PositiveOrZero
    private Integer stock;

    @NotNull
    private Category category;
}
