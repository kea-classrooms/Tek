package com.example.getconnection.Repository;

import com.example.getconnection.Model.Superhero;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("Superhero_stub")
public class StubRepository implements IRepository {
    private List<Superhero> superheroList;

    public StubRepository() {
        superheroList = new ArrayList<Superhero>(List.of(
                new Superhero(100, "Batman", "Bruce Wayne", "1939-05-27", 10),
                new Superhero(101, "Spiderman", "Peter Parker", "2002-06-29", 11),
                new Superhero(102, "WonderWoman", "Prinsesse Diana", "1941-12-01", 12)));
    }

    @Override
    public Superhero getSuperhero(String name) {
        for (Superhero superhero : superheroList) {
            if (superhero.getHero_Name().toLowerCase().equals(name)) {
                return superhero;
            }
        }
        return null;
    }

    @Override
    public List<Superhero> getSuperheroList() {
        return superheroList;
    }
}