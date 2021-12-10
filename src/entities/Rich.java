package entities;

import utilities.*;

import java.util.Objects;

public class Rich extends Person {
    private double money = Math.random() * 100000000;

    public Rich(String name) {
        super(name);
    }

    public void spendMoney() {
        money -= Math.random() * 10000;
    }

    @Override
    public void walkBy(WalkablePlace place) {
        System.out.println("Богач прогуливается в месте '" + place.getName() + "'");
        place.addWalker(this);
    }

    @Override
    public void stopWalking(WalkablePlace place) {
        System.out.println(getName() + " нагулялся");
        place.deleteWalker(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Rich rich = (Rich) obj;

        return getName().equals(rich.getName()) &&
                money == rich.money;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(money);
    }

    @Override
    public String toString() {
        return "Rich '" + getName() + "' has " + money + " dollars";
    }
}
