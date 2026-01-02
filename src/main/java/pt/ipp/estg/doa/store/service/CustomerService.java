package pt.ipp.estg.doa.store.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pt.ipp.estg.doa.store.exception.DuplicateNifException;
import pt.ipp.estg.doa.store.exception.InvalidOperationException;
import pt.ipp.estg.doa.store.exception.ResourceNotFoundException;
import pt.ipp.estg.doa.store.model.dto.CustomerDTO;
import pt.ipp.estg.doa.store.model.entity.Customer;
import pt.ipp.estg.doa.store.model.mapper.CustomerMapper;
import pt.ipp.estg.doa.store.repository.CustomerRepository;

@Service
@AllArgsConstructor
public class CustomerService extends BasedCrudService<Customer, Long, CustomerDTO> {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public CustomerDTO findByEmail(String email) {
        Customer customer = repository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found for email: " + email));
        return toDTO(customer);
    }

    public CustomerDTO findByNif(String nif) {
        Customer customer = repository.findByNif(nif)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found for NIF: " + nif));;
        return toDTO(customer);
    }

    @Override
    public CustomerDTO add(CustomerDTO dto) {
        if (repository.existsByNif(dto.getNif())) {
            throw new DuplicateNifException(
                    "Customer with NIF " + dto.getNif() + " already exists");
        }
        return super.add(dto);
    }

    @Override
    public void delete (Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found. ID: " + id));
        if (customer.getOrders() != null && !customer.getOrders().isEmpty()) {
            throw new InvalidOperationException(
                    "Cannot delete customer with existing orders");
        }
        super.delete(id);
    }

    @Override
    public CustomerRepository getRepository() {
        return repository;
    }

    @Override
    protected String getEntityClassName() {
        return Customer.class.getSimpleName();
    }

    @Override
    protected CustomerDTO toDTO(Customer customer) {
        return mapper.toDTO(customer);
    }

    @Override
    protected Customer toEntity(CustomerDTO dto) {
        return mapper.toEntity(dto);
    }

    @Override
    protected void updateEntity(CustomerDTO dto, Customer customer) {
        mapper.updateEntity(dto, customer);
    }
}
