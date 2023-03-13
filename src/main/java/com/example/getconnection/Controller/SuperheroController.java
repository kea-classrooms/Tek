package com.example.getconnection.Controller;

import com.example.getconnection.DTO.CityWithHeroes;
import com.example.getconnection.DTO.HeroesWithSuperpowers;
import com.example.getconnection.DTO.CountSuperpowerDTO;
import com.example.getconnection.Model.Superhero;
import com.example.getconnection.Repository.IRepository;
import com.example.getconnection.Repository.StubRepository;
import com.example.getconnection.Service.SuperheroService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/superheroes")
public class SuperheroController {
    private SuperheroService superheroServices;
    private IRepository repositories;

    public SuperheroController(ApplicationContext context, @Value("${superhero.repository.impl}") String impl) {
        repositories = (IRepository) context.getBean(impl);
    }

    @GetMapping("getSuper")
    public ResponseEntity<List<Superhero>> getSuperhero() {
        return new ResponseEntity<>(superheroServices.getSuperhero(), HttpStatus.OK);
    }

    @GetMapping("oneSuper")
    public ResponseEntity<List<CountSuperpowerDTO>> oneSuperhero() {
        List<CountSuperpowerDTO> foundHeroes = (List<CountSuperpowerDTO>) superheroServices.oneSuperhero();
        if (foundHeroes == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundHeroes);
        }
    }

    @GetMapping("findPower/{id}")
    public ResponseEntity<HeroesWithSuperpowers> getSuperhero(@PathVariable int id) {
        HeroesWithSuperpowers foundHeroes = (superheroServices.findSuperhero(id));
        if (foundHeroes == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundHeroes);
        }
    }

    @GetMapping("city/{id}")
    public ResponseEntity<List<CityWithHeroes>> findSuperheroAndCity(@PathVariable String id) {
        List<CityWithHeroes> foundHeroes = (superheroServices.findSuperheroesInCity(id));
        if (foundHeroes == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundHeroes);
        }
    }
}

