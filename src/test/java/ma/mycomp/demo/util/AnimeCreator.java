package ma.mycomp.demo.util;

import ma.mycomp.demo.domain.Anime;

public class AnimeCreator {

    public static Anime createAnimeToBeSaved() {
        return Anime.builder()
                .name("tekashi")
                .build();
    }
    public static Anime createValidAnime() {
        return Anime.builder()
                .name("tekashi")
                .id(1)
                .build();
    }
    public static Anime createValidUpdatedAnime() {
        return Anime.builder()
                .name("tekashi_2")
                .id(1)
                .build();
    }

}
