package pt.ipp.estg.doa.store.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {

    private Long id;
    private String name;
    private String nif;
    private String email;
    private String address;
    private String phone;

}
