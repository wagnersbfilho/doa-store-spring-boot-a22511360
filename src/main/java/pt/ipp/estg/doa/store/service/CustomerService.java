package pt.ipp.estg.doa.store.service;

import org.springframework.stereotype.Service;
import pt.ipp.estg.doa.store.exception.DuplicateNifException;
import pt.ipp.estg.doa.store.exception.ResourceNotFoundException;
import pt.ipp.estg.doa.store.model.dto.CustomerDTO;
import pt.ipp.estg.doa.store.model.entity.Customer;
import pt.ipp.estg.doa.store.repository.CustomerRepository;

@Service
public class CustomerService extends BasedCrudService<Customer, Long, CustomerDTO> {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

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
        //TODO after ORDERS
        /*if (orderRepository.existsByCustomerId(id)) {
            throw new InvalidOperationException(
                    "Cannot delete customer with existing orders");
        }*/
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
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getNif(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress()
        );
    }

    @Override
    protected Customer toEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setNif(dto.getNif());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());
        return customer;
    }

    @Override
    protected void updateEntity(CustomerDTO dto, Customer customer) {
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());
    }
}
