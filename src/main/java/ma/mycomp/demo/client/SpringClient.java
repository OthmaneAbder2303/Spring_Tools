package ma.mycomp.demo.client;

import ma.mycomp.demo.domain.Anime;
import ma.mycomp.demo.wrapper.PageableResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringClient {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(SpringClient.class);

//        testGetWithRestTemplate(logger);

        ResponseEntity<PageableResponse<Anime>> exchangeAnimeList = new RestTemplate()
                .exchange("http://localhost:8080/animes?sort=name", HttpMethod.GET, null,
                        new ParameterizedTypeReference<PageableResponse<Anime>>() {});

        PageableResponse<Anime> animePage = exchangeAnimeList.getBody();

        if (animePage != null) {
            animePage.getContent().forEach(x -> logger.info("Anime: {}", x));
            logger.info("Total Pages: {}", animePage.getTotalPages());
        }

//        Anime overlord = Anime.builder().name("Overlord").build();
//        Anime overlordSaved = new RestTemplate()
//                      .postForObject("http://localhost:8080/animes?sort=name", overlord, Anime.class);
//        logger.info("Overlord Saved id : {}", overlordSaved.getId());

        Anime anime = Anime.builder().name("anime").build();
        Anime animeSaved = new RestTemplate()
                .exchange("http://localhost:8080/animes?sort=name", HttpMethod.POST, new HttpEntity<>(anime, createJsonHeader()), Anime.class)
                .getBody();
        logger.info("anime Saved id : {}", animeSaved.getId());


    }

    private static MultiValueMap<String, String> createJsonHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private static void testGetWithRestTemplate(Logger logger) {
        ResponseEntity<Anime> animeResponseEntity = new RestTemplate()
                .getForEntity("http://localhost:8080/animes/{id}", Anime.class, 2);
        logger.info("Response Entity {}", animeResponseEntity);
        logger.info("Response Data {}", animeResponseEntity.getBody());

        Anime anime = new RestTemplate()
                .getForObject("http://localhost:8080/animes/{id}", Anime.class, 2);
        logger.info("Anime {}", anime);
    }
}
