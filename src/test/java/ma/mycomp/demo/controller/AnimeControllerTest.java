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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class AnimeControllerTest {
    @InjectMocks
    private AnimeController animeController;
    @Mock
    private AnimeService animeServiceMocked;

    @BeforeEach
    public void setUp() {
        PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createValidAnime()));
        BDDMockito.when(animeServiceMocked.findAll(ArgumentMatchers.any()))
                .thenReturn(animePage);

        BDDMockito.when(animeServiceMocked.findById(ArgumentMatchers.anyInt()))
                .thenReturn(AnimeCreator.createValidAnime());

        BDDMockito.when(animeServiceMocked.findByName(ArgumentMatchers.any()))
                .thenReturn(List.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(animeServiceMocked.save(AnimeCreator.createAnimeToBeSaved()))
                .thenReturn(AnimeCreator.createValidAnime());

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

}