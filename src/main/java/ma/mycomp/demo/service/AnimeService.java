package ma.mycomp.demo.service;

import ma.mycomp.demo.domain.Anime;
import ma.mycomp.demo.repository.AnimeRepository;
import ma.mycomp.demo.util.Utils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnimeService {

    private final Utils utils;
    private final AnimeRepository animeRepository;

    public AnimeService(Utils utils, AnimeRepository animeRepository) {
        this.utils = utils;
        this.animeRepository = animeRepository;
    }

    public List<Anime> findAll() {
        return animeRepository.findAll();
    }

    public Anime findById(int id) {
        return utils.findAnimeOrThrowNotFound(id, animeRepository);
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }

    public Anime save(Anime anime) {
        return animeRepository.save(anime);
    }

    public void deleteById(int id) {
        animeRepository.delete(utils.findAnimeOrThrowNotFound(id, animeRepository));
    }

    public void update(Anime anime) {
        animeRepository.save(anime);
    }

}
