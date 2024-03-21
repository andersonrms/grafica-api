package grafica.api.domain.Customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import grafica.api.domain.Address.AddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CustomerUpdateDTO(
        Long id,
        String name,
        String email,
        String document,
        AddressDTO address,
        String phone
) {
}
