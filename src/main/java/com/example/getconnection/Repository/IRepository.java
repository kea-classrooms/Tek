package com.example.getconnection.Repository;

import com.example.getconnection.DTO.CityWithHeroes;
import com.example.getconnection.DTO.CountSuperpowerDTO;
import com.example.getconnection.DTO.HeroesWithSuperpowers;
import com.example.getconnection.Model.Superhero;

import java.util.List;

public interface IRepository {
    default Superhero getSuperhero(String name) {
        return null;
    }

    default List<Superhero> getAllSuperheroList() {
        return null;
    }

    default List<CountSuperpowerDTO> getCountSuperpower() {
        return null;
    }

    default CountSuperpowerDTO getAllSuperpowerCount(String name) {
        return null;
    }

    default List<HeroesWithSuperpowers> getHeroesWithSuperpower() {
        return null;
    }

    default HeroesWithSuperpowers getAllHeroSuperpower(String name) {
        return null;
    }

    default List<CityWithHeroes> getCity(String name) {
        return null;
    }

    default List<CityWithHeroes> getAllHeroesInCity(){
        return null;
    }

    List<Superhero> getSuperheroList();
}
