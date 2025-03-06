package ma.mycomp.demo.controller;

import ma.mycomp.demo.domain.Anime;
import ma.mycomp.demo.service.AnimeService;
import ma.mycomp.demo.util.AnimeCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class AnimeControllerTest {
    @InjectMocks
    private AnimeController animeController;
    @Mock
    private AnimeService animeServiceMocked;

    @BeforeEach
    public void setUp() {
        PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createValidAnime()));
        BDDMockito.when(animeServiceMocked.findAll(ArgumentMatchers.any()))
                .thenReturn(animePage);
    }

    @Test
    public void test() {

    }

}