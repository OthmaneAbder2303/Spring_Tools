package ma.mycomp.demo.controller;

import ma.mycomp.demo.domain.Anime;
import ma.mycomp.demo.service.AnimeService;
import ma.mycomp.demo.util.AnimeCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class AnimeControllerTest {
    @InjectMocks //Injecte une instance réelle du contrôleur AnimeController pour le tester.
    private AnimeController animeController;

    @Mock //Crée un mock de AnimeService pour simuler ses comportements sans interagir avec la base de données.
    private AnimeService animeServiceMocked;

    @BeforeEach
    public void setUp() {
        //Avant chaque test, on simule les réponses du AnimeService pour éviter d'accéder à une base de données.
        PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createValidAnime()));
        BDDMockito.when(animeServiceMocked.findAll(ArgumentMatchers.any()))
                .thenReturn(animePage);

        BDDMockito.when(animeServiceMocked.findById(ArgumentMatchers.anyInt()))
                .thenReturn(AnimeCreator.createValidAnime());

        BDDMockito.when(animeServiceMocked.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(animeServiceMocked.save(ArgumentMatchers.any()))
                .thenReturn(AnimeCreator.createValidAnime());

        BDDMockito.doNothing().when(animeServiceMocked).deleteById(ArgumentMatchers.anyInt());

        BDDMockito.doNothing().when(animeServiceMocked).update(ArgumentMatchers.any());

    }

    @Test
    @DisplayName("getAnimes return a pageable list of animes when successful")
    public void getAnimes_ReturnsListOfAnimesInsidePageOfObject_When_Successful() {
        String expectedName = AnimeCreator.createValidAnime().getName();
        Page<Anime> animePage = animeController.getAnimes(null).getBody();

        Assertions.assertThat(animePage).isNotNull();
        Assertions.assertThat(animePage.toList()).isNotEmpty();
        Assertions.assertThat(expectedName).isEqualTo(animePage.toList().get(0).getName());
    }

    @Test
    @DisplayName("getAnimeById return an anime when successful")
    public void getAnimeById_ReturnsAnime_When_Successful() {
        int expectedId = AnimeCreator.createValidAnime().getId();
        Anime anime = animeController.getAnimeById(expectedId).getBody();

        Assertions.assertThat(anime).isNotNull();
        Assertions.assertThat(expectedId).isEqualTo(anime.getId());
    }

//    @Test
//    @DisplayName("getAnimeById return not found when successful")
//    public void getAnimeById_ReturnsNothing_When_unSuccessful() {
//        Anime anime = animeController.getAnimeById(20120).getBody();
//
//        Assertions.assertThat(anime).isNull();
//    }

    @Test
    @DisplayName("getAnimeByName return an anime when successful")
    public void getAnimeByName_ReturnsAnime_When_Successful() {
        String expectedName = AnimeCreator.createValidAnime().getName();
        List<Anime> animes = animeController.getAnimeByName(expectedName).getBody();

        Assertions.assertThat(animes).isNotNull();
        Assertions.assertThat(animes).isNotEmpty();
        Assertions.assertThat(expectedName).isEqualTo(animes.get(0).getName());
    }

    @Test
    @DisplayName("createAnime return an anime when successful")
    public void save_CreatesAnime_When_Successful() {
        Anime anime = AnimeCreator.createAnimeToBeSaved();
        Anime savedAnime = animeController.createAnime(anime).getBody();

        Assertions.assertThat(savedAnime).isNotNull();
        Assertions.assertThat(savedAnime.getName()).isNotEmpty();
        Assertions.assertThat(savedAnime.getName()).isEqualTo(anime.getName());
    }

    @Test
    @DisplayName("deleteAnimeById remove anime when successful")
    public void delete_RemovesAnime_When_Successful() {
        Integer animeId = AnimeCreator.createValidAnime().getId();
        ResponseEntity<Void> responseEntity = animeController.deleteAnimeById(animeId);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        Assertions.assertThat(responseEntity.getBody()).isNull();
    }

    @Test
    @DisplayName("updateAnime updates anime when successful")
    public void update_UpdatesAnime_When_Successful() {
        Anime updatedAnime = AnimeCreator.createValidUpdatedAnime();
        Integer id = updatedAnime.getId();

        // Simule la mise à jour
        BDDMockito.doNothing().when(animeServiceMocked).update(updatedAnime);

        // Simule que la recherche renvoie l'anime mis à jour après la mise à jour
        BDDMockito.when(animeServiceMocked.findById(id))
                .thenReturn(updatedAnime);

        ResponseEntity<Void> responseEntity = animeController.updateAnime(updatedAnime);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        // Vérifie que l’anime récupéré après mise à jour contient bien les nouvelles valeurs
        Anime retrievedAnime = animeController.getAnimeById(id).getBody();
        Assertions.assertThat(retrievedAnime).isNotNull();
        Assertions.assertThat(retrievedAnime.getName()).isEqualTo(updatedAnime.getName());
    }


}