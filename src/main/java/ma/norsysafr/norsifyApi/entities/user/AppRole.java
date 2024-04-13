package ma.norsysafr.norsifyApi.entities.user;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AppRole {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String roleName;
}
