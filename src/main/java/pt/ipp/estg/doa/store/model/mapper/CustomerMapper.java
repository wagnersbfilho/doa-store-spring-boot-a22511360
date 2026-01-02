package pt.ipp.estg.doa.store.model.mapper;

import org.springframework.stereotype.Component;
import pt.ipp.estg.doa.store.model.dto.CustomerDTO;
import pt.ipp.estg.doa.store.model.entity.Customer;

@Component
public class CustomerMapper {

    public CustomerDTO toDTO(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getNif(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress()
        );
    }

    public Customer toEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setNif(dto.getNif());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());
        return customer;
    }

    public void updateEntity(CustomerDTO dto, Customer customer) {
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());
    }
}
