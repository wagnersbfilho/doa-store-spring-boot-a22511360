package pt.ipp.estg.doa.store.model.mapper;

import org.springframework.stereotype.Component;
import pt.ipp.estg.doa.store.model.dto.EarringDTO;
import pt.ipp.estg.doa.store.model.dto.JewelryDTO;
import pt.ipp.estg.doa.store.model.dto.NecklaceDTO;
import pt.ipp.estg.doa.store.model.dto.RingDTO;
import pt.ipp.estg.doa.store.model.entity.Earring;
import pt.ipp.estg.doa.store.model.entity.Jewelry;
import pt.ipp.estg.doa.store.model.entity.Necklace;
import pt.ipp.estg.doa.store.model.entity.Ring;

@Component
public class JewelryMapper {

    public JewelryDTO toDTO(Jewelry entity) {
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

    public Jewelry toEntity(JewelryDTO jewelryDTO) {
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

    public void updateEntity(JewelryDTO jewelryDTO, Jewelry entity) {
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
}
