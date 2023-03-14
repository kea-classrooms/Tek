package com.example.getconnection.Controller;

import com.example.getconnection.DTO.CityWithHeroes;
import com.example.getconnection.DTO.HeroesWithSuperpowers;
import com.example.getconnection.DTO.CountSuperpowerDTO;
import com.example.getconnection.Model.Superhero;
import com.example.getconnection.Repository.IRepository;
import com.example.getconnection.Repository.SuperheroRepository;
import com.example.getconnection.Service.SuperheroService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/superheroes")
public class SuperheroController { //TODO finde ud af hvorfor den viser fejl, når jeg har min getBean metode..
    private SuperheroRepository superheroRepository;
    private SuperheroService superheroService;
    private IRepository repository;

   /* public SuperheroController(ApplicationContext context, @Value("${superherosql.impl}") String impl) {
        repository = (IRepository) context.getBean(impl);
    }*/

    public SuperheroController(SuperheroService superheroService, IRepository repository){
        this.superheroService = superheroService;
        this.repository = repository;
    }

    // Endpoint for at få alle superhelte
    @GetMapping
    public List<Superhero> getAllSuperheroes() {
        return repository.getAllSuperheroes();
    }

    // Endpoint for at få en superhelt efter id
    @GetMapping("/{id}")
    public Superhero getSuperheroById(@PathVariable int id) {
        return repository.getSuperheroById(id);
    }

    // Endpoint for at tilføje en ny superhelt
    @PostMapping
    public void addSuperhero(@RequestBody Superhero superhero) {
        repository.addSuperhero(superhero);
    }

    // Endpoint for at opdatere en superhelt
    @PutMapping("/{id}")
    public void updateSuperhero(@PathVariable int id, @RequestBody Superhero superhero) {
        superhero.setId(id);
        repository.updateSuperhero(superhero);
    }

    // Endpoint for at slette en superhelt
    @DeleteMapping("/{id}")
    public void deleteSuperhero(@PathVariable int id) {
        repository.deleteSuperhero(id);
    }

    @GetMapping("getSuper")
    public ResponseEntity<List<Superhero>> getSuperhero() {
        return new ResponseEntity<>(superheroService.getSuperhero(), HttpStatus.OK);
    }

    @GetMapping("oneSuper")
    public ResponseEntity<List<CountSuperpowerDTO>> oneSuperhero() {
        List<CountSuperpowerDTO> foundHeroes = (List<CountSuperpowerDTO>) superheroService.oneSuperhero();
        if (foundHeroes == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundHeroes);
        }
    }

    @GetMapping("findPower/{id}")
    public ResponseEntity<HeroesWithSuperpowers> getSuperhero(@PathVariable int id) {
        HeroesWithSuperpowers foundHeroes = (superheroService.findSuperhero(id));
        if (foundHeroes == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundHeroes);
        }
    }

    @GetMapping("city/{id}")
    public ResponseEntity<List<CityWithHeroes>> findSuperheroAndCity(@PathVariable String id) {
        List<CityWithHeroes> foundHeroes = (superheroService.findSuperheroesInCity(id));
        if (foundHeroes == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundHeroes);
        }
    }
}
