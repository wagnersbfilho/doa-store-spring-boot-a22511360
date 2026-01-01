package pt.ipp.estg.doa.store.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Transactional
public abstract class BasedCrudService <E, ID, DTO> implements CrudService <ID, DTO> {

    protected abstract JpaRepository<E, ID> getRepository();

    protected abstract DTO toDTO(E entity);

    protected abstract E toEntity(DTO dto);

    protected abstract void updateEntity(DTO dto, E entity);

    @Override
    public DTO findById(ID id) {
        E entity = getRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        return toDTO(entity);
    }

    @Override
    public List<DTO> findAll() {
        return getRepository().findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public DTO add (DTO dto) {
        E entity = toEntity(dto);
        entity = getRepository().save(entity);
        return toDTO(entity);
    }

    @Override
    public DTO update(ID id, DTO dto) {
        E entity = getRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot update. Entity not found"));
        updateEntity(dto, entity);
        return toDTO(entity);
    }

    @Override
    public void delete(ID id) {
        E entity = getRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot delete. Entity not found"));
        getRepository().delete(entity);
    }
}
