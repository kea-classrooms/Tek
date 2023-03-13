package com.example.getconnection.Repository;

import com.example.getconnection.DTO.CityWithHeroes;
import com.example.getconnection.DTO.HeroesWithSuperpowers;
import com.example.getconnection.DTO.CountSuperpowerDTO;
import com.example.getconnection.Model.Superhero;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Repository
public class SuperheroRepository {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String uid;
    @Value("${spring.datasource.password}")
    private String pwd;


    // 1. En superhelt med et bestemt heroName eller en liste med alle superhelte, der indeholder: heroName, realName og creationYear
    public List<Superhero> getSuperhero() {
        List<Superhero> superheroes = new ArrayList<>();
        //TODO En metode, der udskriv produktnavn på et bestemt produktnummer. Produktnummer angives som parameter (tabellen PARTS).
        try (Connection con = DriverManager.getConnection(url, uid, pwd)) {
            String SQL = "SELECT * FROM superhero";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                Superhero superhero = new Superhero(
                        rs.getString("hero_name"),
                        rs.getString("real_name"),
                        rs.getString("creation_year")
                );
                superheroes.add(superhero);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return superheroes;
    }

    // 2. En superhelt med et bestemt heroName eller en liste med alle superhelte, der indeholder: heroName, realName og antallet af superkræfter (Superpower)
    public List<CountSuperpowerDTO> oneSuperhero() {
        List<CountSuperpowerDTO> counts = new ArrayList<>();
        String sql = "SELECT superhero.hero_name, superhero.real_name, COUNT(superpower.superpower_id) AS num_superpowers "
                + "FROM superhero "
                + "LEFT JOIN superhero_superpower ON superhero.superhero_id = superhero_superpower.hero_id "
                + "LEFT JOIN superpower ON superhero_superpower.superpower_id = superpower.superpower_id "
                + "GROUP BY superhero.superhero_id";

        try (Connection conn = DriverManager.getConnection(url, uid, pwd);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String heroName = rs.getString("hero_name");
                String realName = rs.getString("real_name");
                int numSuperpower = rs.getInt("num_superpowers");
                CountSuperpowerDTO count = new CountSuperpowerDTO(heroName, realName, numSuperpower);
                counts.add(count);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return counts;
    }


    // 3. En superhelt med et bestemt heroName eller en liste med alle superhelte, der indeholder: heroName, realName, superkræfter (Superpower)
    public HeroesWithSuperpowers findSuperhero(int id) {
        HeroesWithSuperpowers superhero = null;
        String sql = String.format("SELECT superhero_id, superpower.superpower_id, real_name, hero_name, superpower " +
                "FROM superhero " +
                "INNER JOIN superhero_superpower ON superhero.superhero_id = superhero_superpower.hero_id " +
                "INNER JOIN superpower ON superhero_superpower.superpower_id = superpower.superpower_id " +
                "WHERE superhero.superhero_id = %d;", id);

        try (Connection con = DriverManager.getConnection(url, uid, pwd)) {
            PreparedStatement prepareStatement = con.prepareStatement(sql);

            ResultSet resultSet = prepareStatement.executeQuery();
            String heroName = null;
            String realName = null;
            List<String> superpowers = new ArrayList<>();
            while (resultSet.next()) {
                if (heroName == null && realName == null) {
                    heroName = resultSet.getString("hero_name");
                    realName = resultSet.getString("real_name");
                }
                superpowers.add(resultSet.getString("superpower"));
            }
            superhero = new HeroesWithSuperpowers(heroName, realName, superpowers);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return superhero;
    }


    // 4. En superhelt med et bestemt heroName eller en liste med alle superhelte, der indeholder: heroName og by (City)
    public List<CityWithHeroes> findSuperheroesInCity(String id) {
            List<CityWithHeroes> cityWithHeroesList = new ArrayList<>();
            String sql = "SELECT hero_name, real_name, city.city FROM superhero JOIN city ON superhero.city_id = city.city_id WHERE superhero.superhero_id = ?";

            try (Connection con = DriverManager.getConnection(url, uid, pwd);
                 PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, Integer.parseInt(id));
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    String heroName = rs.getString("hero_name");
                    String realName = rs.getString("real_name");
                    String cityName = rs.getString("city");
                    CityWithHeroes cityWithHeroes = new CityWithHeroes(cityName, new ArrayList<>(Set.of(heroName, realName)));
                    cityWithHeroesList.add(cityWithHeroes);
                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            return cityWithHeroesList;
        }
    }
