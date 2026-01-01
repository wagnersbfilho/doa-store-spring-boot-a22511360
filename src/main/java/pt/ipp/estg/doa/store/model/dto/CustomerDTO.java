package pt.ipp.estg.doa.store.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {

    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Pattern(regexp = "\\d{9}")
    private String nif;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String address;

    @NotBlank
    @Pattern(regexp = "\\d{9}")
    private String phone;

}
