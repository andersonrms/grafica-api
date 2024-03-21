package grafica.api.domain.Product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;
@JsonIgnoreProperties(
        ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public record UpdateProductDTO (
        Long id,
        String name,
        String price,
        boolean active,
        @JsonProperty("min_amount")
        String minAmount
) {
}