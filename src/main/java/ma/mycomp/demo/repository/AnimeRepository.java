package ma.mycomp.demo.repository;

import ma.mycomp.demo.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimeRepository extends JpaRepository<Anime, Integer> {

    List<Anime> findByName(String name);

}
