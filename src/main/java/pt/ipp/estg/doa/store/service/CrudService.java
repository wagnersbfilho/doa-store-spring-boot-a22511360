package pt.ipp.estg.doa.store.service;

import java.util.List;

public interface CrudService<ID, DTO> {

    DTO add(DTO dto);

    DTO update(ID id, DTO dto);

    void delete(ID id);

    DTO findById(ID id);

    List<DTO> findAll();
}
