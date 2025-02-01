package ma.mycomp.demo.repository;

import ma.mycomp.demo.domain.Anime;
import ma.mycomp.demo.util.Utils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Repository
public class AnimeService {

    private final Utils utils;
    private static List<Anime> animeList;
    static {
        animeList = new ArrayList<>(List.of(
                new Anime(1, "Othmane"),
                new Anime(2, "Omar"),
                new Anime(3, "Salim")
        ));
    }

    public AnimeService(Utils utils) {
        this.utils = utils;
    }

    public List<Anime> findAll() {
        return animeList;
    }

    public Anime findById(int id) {
        return utils.findAnimeOrThrowNotFound(id, animeList);
    }

    public Anime save(Anime anime) {
        anime.setId(ThreadLocalRandom.current().nextInt(4, 1000));
        animeList.add(anime);
        return anime;
    }

    public void deleteById(int id) {
        animeList.remove(utils.findAnimeOrThrowNotFound(id, animeList));
    }

    public void update(Anime anime) {
        animeList.remove(utils.findAnimeOrThrowNotFound(anime.getId(), animeList));
        animeList.add(anime);
    }

}
