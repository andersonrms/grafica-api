package grafica.api.domain.Customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import grafica.api.domain.Address.Address;
import grafica.api.domain.Address.AddressDTO;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
@JsonIgnoreProperties(
        ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public record CustomerDTO(
        Long id,
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String document,
        @NotNull
        @Valid
        AddressDTO address,
        @NotBlank
        String phone,
        @JsonProperty("created_at")
        @JsonIgnore
        LocalDateTime createdAt,
        @JsonProperty("updated_at")
        @JsonIgnore
        LocalDateTime updatedAt
) {
        public CustomerDTO(Customer customer){
                this(customer.getId(), customer.getName(), customer.getEmail(), customer.getDocument(), new AddressDTO(customer.getAddress()), customer.getPhone(), customer.getCreatedAt(), customer.getUpdatedAt());
        }
}
