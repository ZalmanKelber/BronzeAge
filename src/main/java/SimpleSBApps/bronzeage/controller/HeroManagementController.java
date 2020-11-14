package SimpleSBApps.bronzeage.controller;

import SimpleSBApps.bronzeage.model.Hero;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/management/heroes")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class HeroManagementController {

    private static List<Hero> SAMPLE_HEROES = Arrays.asList(
            new Hero(1, "Ariadne", "Minos", "Knossos"),
            new Hero(2, "Creon", "Cadmus", "Thebes"),
            new Hero(3, "Pentheus", "Cadmus", "Thebes"),
            new Hero(4, "Merope", "Polybus", "Corinth")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_ADMIN_TRAINEE')")
    public List<Hero> getAllHeroes() {
        return SAMPLE_HEROES;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('prophecy:write')")
    public void addHero(@RequestBody Map<String, String> payload) {
        Hero hero = new Hero(Integer.parseInt(payload.get("id")), payload.get("name"));
        System.out.println(hero);

    }

    @DeleteMapping(path="/{heroId}")
    @PreAuthorize("hasAuthority('prophecy:write')")
    public void deleteHeroById(@PathVariable("heroId") Integer heroId) {
        System.out.println(heroId);
    }

    @PutMapping(path="/{heroId}")
    @PreAuthorize("hasAuthority('prophecy:write')")
    public void updateHeroById(@PathVariable("heroId") Integer heroId, @RequestBody Map<String, String> payload) {
        Hero hero = new Hero(Integer.parseInt(payload.get("id")), payload.get("name"));
        System.out.println(heroId + " " + hero);
    }

}
