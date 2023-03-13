package com.example.getconnection.Repository;

import com.example.getconnection.DTO.CityWithHeroes;
import com.example.getconnection.DTO.CountSuperpowerDTO;
import com.example.getconnection.DTO.HeroesWithSuperpowers;
import com.example.getconnection.Model.Superhero;

import java.util.List;

public interface IRepository {

    public Superhero getSuperheroById(int id);
    public void addSuperhero(Superhero superhero);
    public void updateSuperhero(Superhero superhero);
    public void deleteSuperhero(int id);
    public List<Superhero> getAllSuperheroes();
}