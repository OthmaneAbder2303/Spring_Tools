package ma.mycomp.demo.integration;

import ma.mycomp.demo.domain.Anime;
import ma.mycomp.demo.repository.AnimeRepository;
import ma.mycomp.demo.util.AnimeCreator;
import ma.mycomp.demo.wrapper.PageableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AnimeControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @MockitoBean
    private AnimeRepository animeRepositoryMocked;

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

    }

    @Test
    @DisplayName("getAnimes return a pageable list of animes when successful")
    public void getAnimes_ReturnsListOfAnimesInsidePageOfObject_When_Successful() {
        String expectedName = AnimeCreator.createValidAnime().getName();
        //@formatter:off
        Page<Anime> animePage = testRestTemplate
                .exchange("/animes", HttpMethod.GET, null, new ParameterizedTypeReference<PageableResponse<Anime>>() {
                }).getBody();
        //@formatter:on

        Assertions.assertThat(animePage).isNotNull();
        Assertions.assertThat(animePage.toList()).isNotEmpty();
        Assertions.assertThat(expectedName).isEqualTo(animePage.toList().get(0).getName());
    }

    @Test
    @DisplayName("getAnimeById return an anime when successful")
    public void getAnimeById_ReturnsAnime_When_Successful() {
        int expectedId = AnimeCreator.createValidAnime().getId();
        Anime anime = testRestTemplate.getForObject("/animes/2", Anime.class);

        Assertions.assertThat(anime).isNotNull();
        Assertions.assertThat(expectedId).isEqualTo(anime.getId());
    }

    @Test
    @DisplayName("getAnimeByName returns a list of animes when successful")
    public void getAnimeByName_ReturnsListOfAnimes_When_Successful() {
        String expectedName = "othmane";

        //formatter:off
        List<Anime> animes = testRestTemplate.exchange("http://localhost:8080/animes/name?name=othmane", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Anime>>() {
                }).getBody();
        //formatter:on

        System.out.println("Réponse de l'API : " + animes);

        Assertions.assertThat(animes).isNotNull();
        Assertions.assertThat(animes).isNotEmpty();
        Assertions.assertThat(expectedName).isEqualTo(animes.get(0).getName());
    }

    @Test
    @DisplayName("createAnime return an anime when successful")
    public void save_CreatesAnime_When_Successful() {
        Anime anime = AnimeCreator.createAnimeToBeSaved();
        Anime savedAnime = testRestTemplate.postForObject("/animes", anime, Anime.class);

        Assertions.assertThat(savedAnime).isNotNull();
        Assertions.assertThat(savedAnime.getName()).isNotEmpty();
        Assertions.assertThat(savedAnime.getName()).isEqualTo(anime.getName());
    }

    @Test
    @DisplayName("deleteAnimeById remove anime when successful")
    public void delete_RemovesAnime_When_Successful() {
        Integer animeId = AnimeCreator.createValidAnime().getId();
        ResponseEntity<Void> responseEntity = testRestTemplate.exchange("/animes/" + animeId, HttpMethod.DELETE,
                null, Void.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        Assertions.assertThat(responseEntity.getBody()).isNull();
    }

    @Test
    @DisplayName("updateAnime updates anime when successful")
    public void update_UpdatesAnime_When_Successful() {
        Anime validAnime = AnimeCreator.createValidAnime();
        ResponseEntity<Void> responseEntity = testRestTemplate.exchange("/animes", HttpMethod.PUT,
                create(validAnime), Void.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        Assertions.assertThat(responseEntity.getBody()).isNull();
    }

    private HttpEntity<Anime> create(Anime anime) {
        return new HttpEntity<>(anime, createJsonHeader());
    }

    private static HttpHeaders createJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }


}
