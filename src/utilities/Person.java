package utilities;

import entities.*;
import exceptions.HasNotHomeException;

import java.util.Objects;

public abstract class Person implements ObjectInterface {
    private final String name;
    private City home;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void walkBy(WalkablePlace place);

    public abstract void stopWalking(WalkablePlace place);

    public void setHome(City city) {
        this.home = city;
        city.addCitizen(this);
    }

    public City getHome() throws HasNotHomeException {
        if (home != null) {
            return home;
        }
        throw new HasNotHomeException("Персонаж " + getName() + " числанулся после первого семестра и, очевидно, теперь у него нет дома");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Person person = (Person) obj;

        return name.equals(person.name) &&
                home.equals(person.home);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, home);
    }

    @Override
    public String toString() {
        return "Person's name is '" + name + "'; Home city is '" + home.getName() + "'";
    }
}
