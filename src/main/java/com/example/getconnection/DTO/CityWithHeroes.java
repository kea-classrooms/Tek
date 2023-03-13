package com.example.getconnection.DTO;

import java.util.List;

public class CityWithHeroes {
    private String city;
    private List<String> heroList;


    public CityWithHeroes(String city, List <String> heroList) {
        this.city = city;
        this.heroList = heroList;
    }

    public String getCity() {
        return city;
    }

    public List<String> getCities() {
        return heroList;
    }

    public void addSuperhero(String name){
        heroList.add(name);
    }


}
