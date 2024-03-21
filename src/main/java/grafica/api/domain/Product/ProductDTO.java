package grafica.api.domain.Product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
@JsonIgnoreProperties(
        ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public record ProductDTO (
        Long id,
        @NotBlank /* NOT EMPTY AND NOT NULL ONLY STRING  */
        String name,
        @NotBlank
        String price,
        @NotBlank
        @JsonProperty("min_amount")
        String minAmount,
        @JsonProperty("created_at")
        @JsonIgnore /* IGNORA SERIALIZACAO DO JSON */
        LocalDateTime createdAt,
        @JsonProperty("updated_at")
        @JsonIgnore
        LocalDateTime updatedAt) {

        public ProductDTO(Product product) {
                this(product.getId(), product.getName(), product.getPrice(), product.getMinAmount(), product.getCreatedAt(), product.getUpdatedAt());
        }
}