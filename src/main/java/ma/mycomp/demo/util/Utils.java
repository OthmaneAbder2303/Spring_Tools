package ma.mycomp.demo.util;

import ma.mycomp.demo.domain.Anime;
import ma.mycomp.demo.exception.ResourceNotFoundException;
import ma.mycomp.demo.repository.AnimeRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Utils {

    public static String formatLocalDateTimeToDatabaseStyle(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime);
    }

    public Anime findAnimeOrThrowNotFound(int id, AnimeRepository animeRepository) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Anime Not Found"));
    }
}
