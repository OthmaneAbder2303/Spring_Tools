package ma.mycomp.demo.util;

import ma.mycomp.demo.domain.Anime;

public class AnimeCreator {

    public static Anime createAnimeToBeSaved() {
        return Anime.builder()
                .name("Tekashi")
                .build();
    }
    public static Anime createValidAnime() {
        return Anime.builder()
                .name("Tekashi")
                .id(1)
                .build();
    }
    public static Anime createValidUpdatedAnime() {
        return Anime.builder()
                .name("Tekashi 2")
                .id(1)
                .build();
    }

}
