package SimpleSBApps.bronzeage.controller;

import SimpleSBApps.bronzeage.model.Hero;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/management/heroes")
public class HeroManagementController {

    private static List<Hero> SAMPLE_HEROES = Arrays.asList(
            new Hero(1, "Ariadne", "Minos", "Knossos"),
            new Hero(2, "Creon", "Cadmus", "Thebes"),
            new Hero(3, "Pentheus", "Cadmus", "Thebes"),
            new Hero(4, "Merope", "Polybus", "Corinth")
    );

    @GetMapping
    public List<Hero> getAllHeroes() {
        return SAMPLE_HEROES;
    }

    @PostMapping
    public void addHero(@RequestBody Map<String, String> payload) {
        Hero hero = new Hero(Integer.parseInt(payload.get("id")), payload.get("name"));
        System.out.println(hero);

    }

    @DeleteMapping(path="/{heroId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteHeroById(@PathVariable("heroId") Integer heroId) {
        System.out.println(heroId);
    }

    @PutMapping(path="/{heroId}")
    public void updateHeroById(@PathVariable("heroId") Integer heroId, @RequestBody Map<String, String> payload) {
        Hero hero = new Hero(Integer.parseInt(payload.get("id")), payload.get("name"));
        System.out.println(heroId + " " + hero);
    }

}
