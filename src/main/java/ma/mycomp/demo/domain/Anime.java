package ma.mycomp.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Entity
@Builder
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @NotNull
    @NotEmpty(message = "The Anime's name cannot be empty")
    public String name;

    public Anime(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    public Anime() {
        super();
    }

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Override
    public String toString() {
        return "Anime{id=" + id + ", name='" + name + "'}";
    }


}
