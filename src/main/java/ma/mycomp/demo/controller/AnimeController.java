package ma.mycomp.demo.controller;

import jakarta.validation.Valid;
import ma.mycomp.demo.domain.Anime;
import ma.mycomp.demo.service.AnimeService;
import ma.mycomp.demo.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("animes")
public class AnimeController {

    private static final Logger log = LoggerFactory.getLogger(AnimeController.class);
    private final Utils dateUtil;
    private final AnimeService animeService;

    public AnimeController(Utils dateUtil, AnimeService animeService) {
        this.dateUtil = dateUtil;
        this.animeService = animeService;
    }

    @GetMapping
    public ResponseEntity<Page<Anime>> getAnimes(Pageable pageable) {
        log.info("Formatting the Date {}", dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.findAll(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> getAnimeById(@PathVariable int id) {
        return ResponseEntity.ok(animeService.findById(id));
    }

    @GetMapping(path = "/name")
    public ResponseEntity<List<Anime>> getAnimeByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(animeService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<Anime> createAnime(@RequestBody @Valid Anime anime) {
        return ResponseEntity.ok(animeService.save(anime));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteAnimeById(@PathVariable int id) {
        animeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> updateAnime(@RequestBody Anime anime) {
        animeService.update(anime);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}