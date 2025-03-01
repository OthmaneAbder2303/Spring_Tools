package ma.mycomp.demo.service;

import ma.mycomp.demo.domain.Anime;
import ma.mycomp.demo.repository.AnimeRepository;
import ma.mycomp.demo.util.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AnimeService {

    private final Utils utils;
    private final AnimeRepository animeRepository;

    public AnimeService(Utils utils, AnimeRepository animeRepository) {
        this.utils = utils;
        this.animeRepository = animeRepository;
    }

//    public List<Anime> findAll() {
//        return animeRepository.findAll();
//    }

    public Page<Anime> findAll(Pageable pageable) {
        return animeRepository.findAll(pageable);
    }

    public Anime findById(int id) {
        return utils.findAnimeOrThrowNotFound(id, animeRepository);
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }

    @Transactional(rollbackFor = Exception.class)
    public Anime save(Anime anime) {
        Anime save = animeRepository.save(anime);
//        if(true)
//            throw new RuntimeException("Bad Code");
        return save;
        // the rollback only for RuntimeException
    }

    public void deleteById(int id) {
        animeRepository.delete(utils.findAnimeOrThrowNotFound(id, animeRepository));
    }

    public void update(Anime anime) {
        animeRepository.save(anime);
    }

}
