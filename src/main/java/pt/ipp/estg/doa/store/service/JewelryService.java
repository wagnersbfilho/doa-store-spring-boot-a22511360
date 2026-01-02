package pt.ipp.estg.doa.store.service;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pt.ipp.estg.doa.store.exception.ResourceNotFoundException;
import pt.ipp.estg.doa.store.model.dto.*;
import pt.ipp.estg.doa.store.model.entity.*;
import pt.ipp.estg.doa.store.model.enums.Category;
import pt.ipp.estg.doa.store.model.enums.JewelryType;
import pt.ipp.estg.doa.store.model.mapper.JewelryMapper;
import pt.ipp.estg.doa.store.repository.JewelryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class JewelryService extends BasedCrudService<Jewelry, Long, JewelryDTO> {

    private final JewelryRepository repository;
    private final JewelryMapper mapper;

    @Override
    protected JpaRepository<Jewelry, Long> getRepository() {
        return this.repository;
    }

    public List<JewelryDTO> findByType(String type) {
        try {
            JewelryType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Jewelry type " + type + " not found");
        }

        List<Jewelry> result = repository.findByType(JewelryType.valueOf(type.toUpperCase()))
                .orElseThrow(() -> new ResourceNotFoundException("Jewelry not found for Type: " + type));
        return result.stream()
                .map(this::toDTO)
                .toList();
    }

    public List<JewelryDTO> findByCategory(String category) {
        try {
            Category.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Jewelry type " + category + " not found");
        }

        List<Jewelry> result = repository.findByCategory(Category.valueOf(category.toUpperCase()))
                .orElseThrow(() -> new ResourceNotFoundException("Jewelry not found for Category: " + category));
        return result.stream()
                .map(this::toDTO)
                .toList();
    }

    public List<JewelryDTO> findInStock() {
        List<Jewelry> result = repository.findByStockGreaterThan(0).orElse(new ArrayList<>());
        return result.stream()
                .map(this::toDTO)
                .toList();
    }

    public JewelryDTO updatePrice(Long id, JewelryUpdateDTO dto) {
        Jewelry entity = getRepository().findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot update. " + getEntityClassName() + " not found. ID: " + id));
        entity.setPrice(dto.getPrice());
        return toDTO(entity);
    }

    public JewelryDTO updateStock(Long id, JewelryUpdateDTO dto) {
        Jewelry entity = getRepository().findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot update. " + getEntityClassName() + " not found. ID: " + id));
        entity.setStock(dto.getStock());
        return toDTO(entity);
    }

    @Override
    protected JewelryDTO toDTO(Jewelry entity) {
        return mapper.toDTO(entity);
    }

    @Override
    protected Jewelry toEntity(JewelryDTO jewelryDTO) {
        return mapper.toEntity(jewelryDTO);
    }

    @Override
    protected void updateEntity(JewelryDTO jewelryDTO, Jewelry entity) {
        mapper.updateEntity(jewelryDTO, entity);
    }

    @Override
    protected String getEntityClassName() {
        return Jewelry.class.getSimpleName();
    }
}
