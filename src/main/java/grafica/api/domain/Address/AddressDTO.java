package grafica.api.domain.Address;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
@JsonIgnoreProperties(
        ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public record AddressDTO(
        @NotBlank
        String street,
        @NotBlank
        String neighborhood,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,
        @NotBlank
        String uf,
        @NotBlank
        String city,
        String number,
        String complement) {

        public AddressDTO(Address address) {
                this(address.getStreet(), address.getNeighborhood(), address.getCep(), address.getUf(), address.getCity(), address.getNumber(), address.getComplement());
        }
}