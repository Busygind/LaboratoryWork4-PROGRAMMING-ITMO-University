package entities;

import exceptions.ToManyRichesException;
import utilities.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class City implements Observable {
    private final String name;
    private ArrayList<Person> citizens = new ArrayList<>();
    private ArrayList<Street> streets = new ArrayList<>();
    private Rich[] riches = new Rich[8];
    private boolean beMostBeatiful;
    private boolean goodWeather;

    public City(String name) {
        this.name = name;
        setBeauty();
        setWeather();
        joinStory();
    }

    public void setBeauty() {
        if (this.getName().equals("Лос-Паганос")) {
            this.beMostBeatiful = true;
        }
    }

    static int countOfRiches = 0;

    public void addCitizen(Person citizen) throws ToManyRichesException {
        try {
            citizens.add(citizen);
            if (citizen instanceof Rich) {
                riches[countOfRiches] = (Rich) citizen;
                countOfRiches += 1;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ToManyRichesException("В этом городе уже хватает богачей");
        }
    }

    public void deleteCitizen(Person citizen) {
        citizens.remove(citizen);
    }

    public void addStreet(Street street) {
        streets.add(street);
    }

    public void setWeather() {
        //Климат замечательный, поэтому погода хорошая в 90% случаев
        goodWeather = Math.random() > 0.1;
    }

    public boolean getWeather() {
        return goodWeather;
    }

    private void joinStory() {
        if (beMostBeatiful) {
            System.out.println("Красивейший город '" + getName() + "' присоединился к истории.");
        } else {
            System.out.println("Город '" + getName() + "' присоединился к истории.");
        }
    }

    @Override
    public void observedBy(Person person) {
        System.out.println(person.getName() + " любуется морем");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        City city = (City) obj;

        return getName().equals(city.getName()) &&
                citizens.equals(city.citizens) &&
                Arrays.equals(riches, city.riches) &&
                streets.equals(city.streets) &&
                beMostBeatiful == city.beMostBeatiful &&
                goodWeather == city.goodWeather;
    }

    @Override
    public int hashCode() {
        return Objects.hash(citizens, Arrays.hashCode(riches), streets);
    }

    @Override
    public String toString() {
        return "City called '" + getName() + "'; Count of streets: " + streets.toArray().length + "; " +
                "Count of citizens: " + citizens.toArray().length + ";";

    }
}