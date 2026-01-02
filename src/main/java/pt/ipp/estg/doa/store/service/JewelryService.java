package pt.ipp.estg.doa.store.service;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pt.ipp.estg.doa.store.exception.ResourceNotFoundException;
import pt.ipp.estg.doa.store.model.dto.*;
import pt.ipp.estg.doa.store.model.entity.*;
import pt.ipp.estg.doa.store.model.enums.Category;
import pt.ipp.estg.doa.store.model.enums.JewelryType;
import pt.ipp.estg.doa.store.repository.JewelryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class JewelryService extends BasedCrudService<Jewelry, Long, JewelryDTO> {

    private final JewelryRepository repository;

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
        if (entity instanceof Earring) {
            Earring earring = (Earring) entity;
            return new EarringDTO(
                    earring.getId(),
                    earring.getName(),
                    earring.getMaterial(),
                    earring.getWeight(),
                    earring.getPrice(),
                    earring.getStock(),
                    earring.getCategory(),
                    earring.getClaspType()
            );
        }

        if (entity instanceof Necklace) {
            Necklace necklace = (Necklace) entity;
            return new NecklaceDTO(
                    necklace.getId(),
                    necklace.getName(),
                    necklace.getMaterial(),
                    necklace.getWeight(),
                    necklace.getPrice(),
                    necklace.getStock(),
                    necklace.getCategory(),
                    necklace.getLength());
        }

        if (entity instanceof Ring) {
            Ring ring = (Ring) entity;
            return new RingDTO(
                    ring.getId(),
                    ring.getName(),
                    ring.getMaterial(),
                    ring.getWeight(),
                    ring.getPrice(),
                    ring.getStock(),
                    ring.getCategory(),
                    ring.getSize());
        }

        return null;
    }

    @Override
    protected Jewelry toEntity(JewelryDTO jewelryDTO) {
        if (jewelryDTO instanceof EarringDTO) {
            EarringDTO dto = (EarringDTO) jewelryDTO;
            Earring earring = new Earring();
            setJewerlyCommonData(earring, dto);
            earring.setClaspType(dto.getClaspType());
            return earring;
        }
        if  (jewelryDTO instanceof NecklaceDTO) {
            NecklaceDTO dto = (NecklaceDTO) jewelryDTO;
            Necklace necklace = new Necklace();
            setJewerlyCommonData(necklace, dto);
            necklace.setLength(dto.getLength());
            return necklace;
        }
        if  (jewelryDTO instanceof RingDTO) {
            RingDTO dto = (RingDTO) jewelryDTO;
            Ring ring = new Ring();
            setJewerlyCommonData(ring, dto);
            ring.setSize(dto.getSize());
            return ring;
        }
        return null;
    }

    @Override
    protected void updateEntity(JewelryDTO jewelryDTO, Jewelry entity) {
        if (entity instanceof Earring earring) {
            EarringDTO dto = (EarringDTO) jewelryDTO;
            setJewerlyCommonData(earring, dto);
            earring.setClaspType(dto.getClaspType());
        }
        if  (entity instanceof Necklace necklace) {
            NecklaceDTO dto = (NecklaceDTO) jewelryDTO;
            setJewerlyCommonData(necklace, dto);
            necklace.setLength(dto.getLength());
        }
        if  (entity instanceof Ring ring) {
            RingDTO dto = (RingDTO) jewelryDTO;
            setJewerlyCommonData(ring, dto);
            ring.setSize(dto.getSize());
        }
    }

    private static void setJewerlyCommonData(Jewelry jewelry, JewelryDTO dto) {
        jewelry.setId(dto.getId());
        jewelry.setName(dto.getName());
        jewelry.setMaterial(dto.getMaterial());
        jewelry.setWeight(dto.getWeight());
        jewelry.setPrice(dto.getPrice());
        jewelry.setStock(dto.getStock());
        jewelry.setCategory(dto.getCategory());
        jewelry.setType(dto.getType());
    }

    @Override
    protected String getEntityClassName() {
        return Jewelry.class.getSimpleName();
    }
}
