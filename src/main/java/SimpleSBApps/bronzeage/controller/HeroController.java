package SimpleSBApps.bronzeage.controller;

import SimpleSBApps.bronzeage.model.Hero;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/heroes")
public class HeroController {

    private static final List<Hero> SAMPLE_HEROES = Arrays.asList(
            new Hero(1, "Ariadne", "Minos", "Knossos"),
            new Hero(2, "Creon", "Cadmus", "Thebes"),
            new Hero(3, "Pentheus", "Cadmus", "Thebes"),
            new Hero(4, "Merope", "Polybus", "Corinth")
    );

    @GetMapping("/{heroId}")
    public Hero getHero(@PathVariable("heroId") Integer heroId) {
        return SAMPLE_HEROES.stream()
                .filter(hero -> hero.getId().equals(heroId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("couldn't find user"));
    }
}
