package entities;

import utilities.*;

import java.util.Objects;

public class MainCharacter extends Person {
    private RestaurantAbstract currentPlace;

    public MainCharacter() {
        super("неопознанный персонаж");
        joinStory();
    }

    public MainCharacter(String name) {
        super(name);
        joinStory();
    }

    public void stopNearTheRestaurant(RestaurantAbstract rest) {
        this.currentPlace = rest;
        System.out.println(getName() + " остановился около ресторана: '" + this.currentPlace.getName() + "'");
    }

    public void serveLunch() {
        System.out.println(getName() + " подает обед прямо в машину");
    }

    private void joinStory() {
        System.out.println("Персонаж '" + getName() + "' присоединилась к истории.");
    }

    public RestaurantAbstract getCurrentPlace() {
        return currentPlace;
    }

    @Override
    public void stopWalking(WalkablePlace place) {
        System.out.println(getName() + " нагулялся");
        place.deleteWalker(this);
    }

    @Override
    public void walkBy(WalkablePlace place) {
        System.out.println(getName() + " начал гулять в месте: '" + place.getName() + "'");
        place.addWalker(this);
    }

    public void jumpOut() {
        System.out.println(getName() + " выскакивает из ресторана");
    }

    @Override
    public String toString() {
        if (currentPlace != null) {
            return "Character '" + getName() + "', now in: '" + currentPlace.getName() + "'";
        }
        return "Character '" + getName() + "'";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        MainCharacter mainCharacter = (MainCharacter) obj;

        return getName().equals(mainCharacter.getName()) && currentPlace.equals(mainCharacter.currentPlace);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(currentPlace);
    }
}
