package pt.ipp.estg.doa.store.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrderItemDTO {

    private Long id;
    private Long idOrder;

    @NotNull
    @Positive
    private Integer quantity;

    @NotNull
    @Positive
    private BigDecimal priceAtPurchase;

    @NotNull
    private Long idJewelry;

    private JewelryDTO jewelry;
}
