package ma.mycomp.demo.client;

import ma.mycomp.demo.domain.Anime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringClient {

    public static void main(String[] args) {
        ResponseEntity<Anime> animeResponseEntity = new RestTemplate().getForEntity("http://localhost:8080/animes/2", Anime.class);
        Logger logger = LoggerFactory.getLogger(SpringClient.class);
        logger.info("Response Entity {}",animeResponseEntity.getBody());
    }
}
