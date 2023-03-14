package com.example.getconnection.Repository;

import com.example.getconnection.DTO.CityWithHeroes;
import com.example.getconnection.DTO.CountSuperpowerDTO;
import com.example.getconnection.DTO.HeroesWithSuperpowers;
import com.example.getconnection.Model.Superhero;

import java.util.List;

public interface IRepository {

    Superhero getSuperheroById(int id);

    void addSuperhero(Superhero superhero);

    void updateSuperhero(Superhero superhero);

    void deleteSuperhero(int id);

    List<Superhero> getAllSuperheroes();
}