package grafica.api.domain.User;

import jakarta.persistence.*;
import lombok.*;

@Table(name="users")
@Entity(name="User")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
}
