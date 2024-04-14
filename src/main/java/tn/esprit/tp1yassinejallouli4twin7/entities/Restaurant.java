package tn.esprit.tp1yassinejallouli4twin7.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@Entity
public class Restaurant  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idResto;

    private String nomResto;

    private String descriptionResto;

    private String telResto;
@JsonIgnore
    @OneToMany(mappedBy = "resto", fetch = FetchType.EAGER)
    private Set<Foyer> foyers;

}
