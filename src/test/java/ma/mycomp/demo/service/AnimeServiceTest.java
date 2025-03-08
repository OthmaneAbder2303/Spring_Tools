package ma.mycomp.demo.service;

import ma.mycomp.demo.domain.Anime;
import ma.mycomp.demo.repository.AnimeRepository;
import ma.mycomp.demo.util.AnimeCreator;
import ma.mycomp.demo.util.Utils;
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
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class AnimeServiceTest {
    @InjectMocks
    private AnimeService animeServiceMocked;

    @Mock
    private AnimeRepository animeRepositoryMocked;

    @Mock
    private Utils utilsMocked;

    @BeforeEach
    public void setUp() {
        PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(animeRepositoryMocked.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(animePage);

        BDDMockito.when(animeRepositoryMocked.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(animeRepositoryMocked.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(animeRepositoryMocked.save(ArgumentMatchers.any()))
                .thenReturn(AnimeCreator.createValidAnime());

        BDDMockito.doNothing().when(animeRepositoryMocked).deleteById(ArgumentMatchers.anyInt());

        BDDMockito.when(utilsMocked.findAnimeOrThrowNotFound(ArgumentMatchers.anyInt(), ArgumentMatchers.any(AnimeRepository.class)))
                .thenReturn(AnimeCreator.createValidAnime());
    }

    @Test
    @DisplayName("findAll return a pageable list of animes when successful")
    public void findAll_ReturnsListOfAnimesInsidePageOfObject_When_Successful() {
        String expectedName = AnimeCreator.createValidAnime().getName();
        Page<Anime> animePage = animeServiceMocked.findAll(PageRequest.of(0,10));

        Assertions.assertThat(animePage).isNotNull();
        Assertions.assertThat(animePage.toList()).isNotEmpty();
        Assertions.assertThat(expectedName).isEqualTo(animePage.toList().get(0).getName());
    }

    @Test
    @DisplayName("findById return an anime when successful")
    public void findById_ReturnsAnime_When_Successful() {
        int expectedId = AnimeCreator.createValidAnime().getId();
        Anime anime = animeServiceMocked.findById(expectedId);

        Assertions.assertThat(anime).isNotNull();
        Assertions.assertThat(expectedId).isEqualTo(anime.getId());
    }

    @Test
    @DisplayName("findByName return an anime when successful")
    public void findByName_ReturnsAnime_When_Successful() {
        String expectedName = AnimeCreator.createValidAnime().getName();
        List<Anime> animes = animeServiceMocked.findByName(expectedName);

        Assertions.assertThat(animes).isNotNull();
        Assertions.assertThat(animes).isNotEmpty();
        Assertions.assertThat(expectedName).isEqualTo(animes.get(0).getName());
    }

    @Test
    @DisplayName("save return an anime when successful")
    public void save_CreatesAnime_When_Successful() {
        Anime anime = AnimeCreator.createAnimeToBeSaved();
        Anime savedAnime = animeServiceMocked.save(anime);

        Assertions.assertThat(savedAnime).isNotNull();
        Assertions.assertThat(savedAnime.getName()).isNotEmpty();
        Assertions.assertThat(savedAnime.getName()).isEqualTo(anime.getName());
    }

    @Test
    @DisplayName("deleteById removes the anime when successful")
    public void deleteById_RemovesAnime_When_Successful() {
        Integer animeId = AnimeCreator.createValidAnime().getId();
        Assertions.assertThatCode(() -> animeServiceMocked.deleteById(animeId))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("saveAnime updates anime when successful")
    public void save_UpdatesAnime_When_Successful() {
        Anime updatedAnime = AnimeCreator.createValidUpdatedAnime();
        Integer animeId = updatedAnime.getId();

        // Simuler la mise à jour
        BDDMockito.when(animeRepositoryMocked.save(ArgumentMatchers.any()))
                .thenReturn(updatedAnime);

        // Simuler la récupération après mise à jour
        BDDMockito.when(animeServiceMocked.findById(animeId))
                .thenReturn(updatedAnime);

        Anime result = animeServiceMocked.save(updatedAnime);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo(updatedAnime.getId());
        Assertions.assertThat(result.getName()).isEqualTo(updatedAnime.getName());
    }
}
