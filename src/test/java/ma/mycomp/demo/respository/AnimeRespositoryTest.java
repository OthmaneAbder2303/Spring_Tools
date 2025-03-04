package ma.mycomp.demo.respository;

import ma.mycomp.demo.domain.Anime;
import ma.mycomp.demo.repository.AnimeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Anime Repository Tests")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class AnimeRespositoryTest {
    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Save creates anime when successful")
    public void save_Persistence_WhenSuccessful() {
        Anime anime = createAnime();
        Anime savedAnime = this.animeRepository.save(anime);
        Assertions.assertThat(savedAnime.getId()).isNotNull();
        Assertions.assertThat(savedAnime.getName()).isNotNull();
        Assertions.assertThat(savedAnime.getName()).isEqualTo(anime.getName());
    }

    @Test
    @DisplayName("Save updates anime when successful")
    public void save_UpdateAnime_WhenSuccessful() {
        Anime anime = createAnime();
        Anime savedAnime = this.animeRepository.save(anime);

        savedAnime.setName("This is an Updated Anime");
        Anime updatedAnime = this.animeRepository.save(savedAnime);

        Assertions.assertThat(savedAnime.getId()).isNotNull();
        Assertions.assertThat(savedAnime.getName()).isNotNull();
        Assertions.assertThat(updatedAnime.getName()).isEqualTo(savedAnime.getName());
    }

    @Test
    @DisplayName("Delete Removes anime when successful")
    public void delete_RemoveAnime_WhenSuccessful() {
        Anime anime = createAnime();
        Anime savedAnime = this.animeRepository.save(anime);

        this.animeRepository.delete(savedAnime);
        Optional<Anime> animeOptional = this.animeRepository.findById(savedAnime.getId());

        Assertions.assertThat(savedAnime.getId()).isNotNull();
        Assertions.assertThat(savedAnime.getName()).isNotNull();
        Assertions.assertThat(animeOptional.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Find By Name returns anime when successful")
    public void findByName_ReturnsAnime_WhenSuccessful() {
        Anime anime = createAnime();
        Anime savedAnime = this.animeRepository.save(anime);

        String name = savedAnime.getName();
        List<Anime> animeList = this.animeRepository.findByName(name);

        Assertions.assertThat(animeList).isNotEmpty();
        Assertions.assertThat(animeList).contains(savedAnime);
    }

    private Anime createAnime(){
        return Anime.builder()
                .name("Test Anime")
                .build();
    }
}
