package ma.mycomp.demo.controller;

import ma.mycomp.demo.domain.Anime;
import ma.mycomp.demo.service.AnimeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/anime-view")
public class AnimeViewController {

    private final AnimeService animeService;

    public AnimeViewController(AnimeService animeService) {
        this.animeService = animeService;
    }

    @GetMapping("/{id}")
    public String getAnimeView(@PathVariable int id, Model model) {
        Anime anime = animeService.findById(id);
        model.addAttribute("anime", anime);  // Pass anime object to Thymeleaf
        return "anime-page";  // Returns Thymeleaf template (anime-page.html)
    }
}

