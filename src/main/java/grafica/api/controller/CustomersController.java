package grafica.api.controller;

import grafica.api.domain.Customer.Customer;
import grafica.api.domain.Customer.CustomerDTO;
import grafica.api.domain.Customer.CustomerRepository;
import grafica.api.domain.Customer.CustomerUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
public class CustomersController {

    @Autowired
    private CustomerRepository repository;

    @PostMapping()
    @Transactional
    public void create(@Valid @RequestBody CustomerDTO customerDTO){
        var customer = new Customer(customerDTO);
        repository.save(customer);
    }

    @GetMapping()
    public Page<CustomerDTO> list(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable){
        return repository.findAllByActiveTrue(pageable).map(CustomerDTO::new);
    }

    @PutMapping()
    @Transactional
    public CustomerDTO update(@Valid @RequestBody CustomerDTO customerDTO){
        var customer = repository.getReferenceById(customerDTO.id());
        customer.update(customerDTO);

        return new CustomerDTO(customer);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id){
        var customer = repository.getReferenceById(id);
        customer.delete();
    }
}
