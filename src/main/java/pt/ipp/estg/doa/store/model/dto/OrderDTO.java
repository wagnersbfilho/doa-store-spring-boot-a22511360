package pt.ipp.estg.doa.store.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import pt.ipp.estg.doa.store.model.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderDTO {

    private Long id;
    private LocalDate orderDate;
    private OrderStatus status;

    @NotNull
    private Long idCustomer;

    @NotNull
    private List<OrderItemDTO> orderItems;

    private CustomerDTO customer;
    private List<PaymentDTO> payments;
}
