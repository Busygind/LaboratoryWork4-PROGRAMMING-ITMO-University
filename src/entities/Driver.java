package entities;

import utilities.*;

import java.util.Objects;

public class Driver extends Person {
    private Infrastructure currentPlace;
    private double money;
    public boolean hasCar;

    public Driver(String name) {
        super(name);
        this.money = Math.random() * 100000;
    }

    public void buyCar() {
        class Car {
            private final double price;

            public Car() {
                price = Math.random() * 100000;
            }

            public double getPrice() {
                return price;
            }
            //очень глубокое и подробное описание строения автомобиля
        }
        Car car = new Car();
        if (car.getPrice() < this.money) {
            this.hasCar = true;
        }
    }

    public void beHappy(MainCharacter waiter) {
        if (this.hasCar) {
            System.out.println("Всем автолюбителям это понравилось");
            getSignal(waiter);
        }
    }

    public void getSignal(MainCharacter waiter) {
        System.out.println("Автолюбители дают сигнал");
        if (Objects.equals(waiter.getName(), "Официант")) {
            waiter.jumpOut();
            waiter.serveLunch();
        }
    }

    @Override
    public void stopWalking(WalkablePlace place) {
        System.out.println("Водитель нагулялся в месте: '" + this.currentPlace.getName() + "'");
        currentPlace.deleteWalker(this);
        this.currentPlace = null;
    }

    @Override
    public void walkBy(WalkablePlace place) {
        System.out.println("Водитель прогуливается в месте '" + place.getName() + "'");
        place.addWalker(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Driver driver = (Driver) obj;

        return getName().equals(driver.getName()) &&
                (currentPlace.getName()).equals(driver.getName()) &&
                money == driver.money &&
                hasCar == driver.hasCar;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(currentPlace, hasCar, money);
    }

    @Override
    public String toString() {
        return "Driver '" + getName() + "', current place: '" + currentPlace.getName() + "'; " +
                "current savings: " + money + " dollars";
    }
}