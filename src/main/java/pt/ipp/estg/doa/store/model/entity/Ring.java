package pt.ipp.estg.doa.store.model.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ring extends Jewelry {

    @NotNull
    @Min(10)
    @Max(30)
    private Integer size;
}
