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
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("customer")
public class CustomersController {

    @Autowired
    private CustomerRepository repository;

    @PostMapping()
    @Transactional
    public ResponseEntity create(@Valid @RequestBody CustomerDTO customerDTO, UriComponentsBuilder builder){
        var customer = new Customer(customerDTO);
        repository.save(customer);
        var uri = builder.path("/customer/{id}").buildAndExpand(customer.getId()).toUri();

        return ResponseEntity.created(uri).body(new CustomerDTO(customer));
    }

    @GetMapping()
    public ResponseEntity<Page<CustomerDTO>> list(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable){
        var page =  repository.findAllByActiveTrue(pageable).map(CustomerDTO::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity list(@PathVariable Long id){
        var customer = repository.getReferenceById(id);

        return ResponseEntity.ok(new CustomerDTO(customer));
    }

    @PutMapping()
    @Transactional
    public ResponseEntity<CustomerDTO> update(@Valid @RequestBody CustomerDTO customerDTO){
        var customer = repository.getReferenceById(customerDTO.id());
        customer.update(customerDTO);

        return ResponseEntity.ok(new CustomerDTO(customer));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        var customer = repository.getReferenceById(id);
        customer.delete();

        return ResponseEntity.noContent().build();
    }
}
