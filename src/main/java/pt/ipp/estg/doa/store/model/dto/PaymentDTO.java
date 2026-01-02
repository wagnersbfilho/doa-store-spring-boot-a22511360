package pt.ipp.estg.doa.store.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import pt.ipp.estg.doa.store.model.enums.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PaymentDTO {

    private Long id;

    @NotNull
    @Positive
    private BigDecimal amount;

    private LocalDate paymentDate;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private Long idOrder;
}
