package ma.mycomp.demo.client;

import ma.mycomp.demo.domain.Anime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringClient {

    public static void main(String[] args) {
        ResponseEntity<Anime> animeResponseEntity = new RestTemplate()
                .getForEntity("http://localhost:8080/animes/{id}", Anime.class, 14);
        Logger logger = LoggerFactory.getLogger(SpringClient.class);
        logger.info("Response Entity {}",animeResponseEntity);
        logger.info("Response Data {}", animeResponseEntity.getBody());

        Anime anime = new RestTemplate()
                .getForObject("http://localhost:8080/animes/{id}", Anime.class, 14);
        logger.info("Anime {}", anime);

        Anime[] animeArray = new RestTemplate()
                .getForObject("http://localhost:8080/animes", Anime[].class);
//      List<Anime> animeList = Arrays.asList(animeArray);
        logger.info("Anime Array {}", Arrays.toString(animeArray));

        //@formatter:off
        ResponseEntity<List<Anime>> exchangeAnimeList = new RestTemplate()
                .exchange("http://localhost:8080/animes", HttpMethod.GET, null, new ParameterizedTypeReference<List<Anime>>() {
                });
        //@formatter:on
        logger.info("Anime List {}", exchangeAnimeList.getBody());

    }
}
