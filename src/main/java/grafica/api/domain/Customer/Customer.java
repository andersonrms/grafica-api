package grafica.api.domain.Customer;

import grafica.api.domain.Address.Address;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "customers")
@Entity(name = "Customer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String document;
    private Address address;
    private String phone;
    private Boolean active;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Customer(CustomerDTO customerDTO) {
        this.name = customerDTO.name();
        this.email = customerDTO.email();
        this.document = customerDTO.document();
        this.phone = customerDTO.phone();
        this.address = new Address(customerDTO.address());
        this.active = true;
        this.createdAt = LocalDateTime.now();
    }

    public void delete() {
        this.active = false;
        this.updatedAt = LocalDateTime.now();
    }

    public void update(CustomerDTO customerDTO) {
        if(customerDTO.name() != null) this.name = customerDTO.name();
        if(customerDTO.email() != null) this.email = customerDTO.email();
        if(customerDTO.document() != null) this.document = customerDTO.document();
        if(customerDTO.address() != null) this.address = new Address(customerDTO.address());
        if(customerDTO.phone() != null) this.phone = customerDTO.phone();

        this.updatedAt = LocalDateTime.now();
    }
}
