package com.example.getconnection.Service;

import com.example.getconnection.DTO.CityWithHeroes;
import com.example.getconnection.DTO.HeroesWithSuperpowers;
import com.example.getconnection.DTO.CountSuperpowerDTO;
import com.example.getconnection.Model.Superhero;
import com.example.getconnection.Repository.SuperheroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

    @Service
    public class SuperheroService {
        private SuperheroRepository superheroRepository;

        public SuperheroService(SuperheroRepository superheroRepository){
        this.superheroRepository = superheroRepository;
        }

        public List<Superhero> getSuperhero() {
            return superheroRepository.getSuperhero();
        }

        public List<CountSuperpowerDTO> oneSuperhero(){
           return superheroRepository.oneSuperhero();
        }


        // Finder en superhero (med superpowers)
        public HeroesWithSuperpowers findSuperhero(int id){
            HeroesWithSuperpowers hero = superheroRepository.findSuperhero(id);
            if (hero == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hero doesn't exist!");
            return hero;
        }


        public List<CityWithHeroes> findSuperheroesInCity(String id){
            return superheroRepository.findSuperheroesInCity(id);
        }


}