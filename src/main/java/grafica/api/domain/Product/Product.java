package grafica.api.domain.Product;

import grafica.api.domain.Product.ProductDTO;
import grafica.api.domain.Product.UpdateProductDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name="products")
@Entity(name="Product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String price;
    @Column(name = "min_amount")
    private String minAmount;
    private Boolean active;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Product(ProductDTO productDTO) {
        this.name = productDTO.name();
        this.price = productDTO.price();
        this.minAmount = productDTO.minAmount();
        this.active = true;
        this.createdAt = LocalDateTime.now();
    }

    public void update(UpdateProductDTO productDTO) {
        if(productDTO.name() != null) this.name = productDTO.name();
        if(productDTO.price() != null) this.price = productDTO.price();
        if(productDTO.minAmount() != null) this.minAmount = productDTO.minAmount();

        this.updatedAt = LocalDateTime.now();
    }

    public void delete() {
        this.active = false;
        this.updatedAt = LocalDateTime.now();
    }
}
