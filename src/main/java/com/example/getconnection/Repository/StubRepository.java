package com.example.getconnection.Repository;

import com.example.getconnection.DTO.HeroesWithSuperpowers;
import com.example.getconnection.Model.Superhero;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("Superhero_stub")
public class StubRepository implements IRepository {
    private List<Superhero> superheroes = new ArrayList<Superhero>();

    public StubRepository() {
        superheroes.add(new Superhero(100, "Batman", "Bruce Wayne", "1939-05-27", 10));
        superheroes.add(new Superhero(101, "Spiderman", "Peter Parker", "2002-06-29", 11));
        superheroes.add(new Superhero(102, "WonderWoman", "Prinsesse Diana", "1941-12-01", 12));
    }

    @Override
    public List<Superhero> getAllSuperheroes() {
        return superheroes;
    }

    @Override
    public Superhero getSuperheroById(int id) {
        for (Superhero superhero : superheroes) {
            if (superhero.getId() == id) {
                return superhero;
            }
        }
        return null;
    }

    @Override
    public void addSuperhero(Superhero superhero) {
        superheroes.add(superhero);
    }

    @Override
    public void updateSuperhero(Superhero superhero) {
        for (int i = 0; i < superheroes.size(); i++) {
            if (superheroes.get(i).getId() == superhero.getId()) {
                superheroes.set(i, superhero);
                break;
            }
        }
    }

    @Override
    public void deleteSuperhero(int id) {
        for (int i = 0; i < superheroes.size(); i++) {
            if (superheroes.get(i).getId() == id) {
                superheroes.remove(i);
                break;
            }
        }
    }
}
