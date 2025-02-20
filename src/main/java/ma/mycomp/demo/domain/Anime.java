package ma.mycomp.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

@Entity
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @NotNull
    @NotEmpty(message = "The Anime's name cannot be empty")
    public String name;
    @URL
    @NotNull
    public String url;

    public Anime(Integer id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }
    public Anime() {
        super();
    }

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getUrl() {
        return url;
    }
    public void setUrl(@URL @NotNull String url) {
        this.url = url;
    }



}
