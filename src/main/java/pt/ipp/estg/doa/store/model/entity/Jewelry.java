package pt.ipp.estg.doa.store.model.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.ipp.estg.doa.store.model.enums.Category;
import pt.ipp.estg.doa.store.model.enums.JewelryType;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Jewelry implements pt.ipp.estg.doa.store.model.entity.Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String material;
    private BigDecimal weight;
    private BigDecimal price;
    private Integer stock;

    @Enumerated(EnumType.STRING)
    private JewelryType type;

    @Enumerated(EnumType.STRING)
    private Category category;
}
