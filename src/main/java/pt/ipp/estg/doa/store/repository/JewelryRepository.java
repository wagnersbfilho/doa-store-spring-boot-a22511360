package pt.ipp.estg.doa.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.estg.doa.store.model.entity.Jewelry;
import pt.ipp.estg.doa.store.model.enums.Category;
import pt.ipp.estg.doa.store.model.enums.JewelryType;

import java.util.List;
import java.util.Optional;

@Repository
public interface JewelryRepository extends JpaRepository<Jewelry, Long> {

    Optional<List<Jewelry>> findByType(JewelryType type);

    Optional<List<Jewelry>> findByCategory(Category category);

    Optional<List<Jewelry>> findByStockGreaterThan(Integer stock);
}
