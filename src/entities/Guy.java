package entities;

import utilities.*;

import java.util.Objects;

// Персонаж, рассуждения которого оказались верными

public class Guy extends Person {
    private boolean calculation;
    private Observable location;

    public Guy(String name) {
        super(name);
        this.verifyCalculation();
        joinStory();
    }

    public Guy(String name, boolean calculation) {
        super(name);
        this.calculation = calculation;
        joinStory();
    }

    public void verifyCalculation() {
        class Calculation {
            private boolean beRight;

            public Calculation(boolean calc) {
                verify(calc);
            }

            public Calculation() {
                beRight = Math.random() > 0.5;
            }

            private void verify(boolean calc) {
                beRight = calc;
            }

            public boolean getCalc() {
                return beRight;
            }
        }
        Calculation calc = new Calculation();
        if (calc.getCalc()) {
            calculation = true;
        } else {
            calculation = false;
        }
    }

    private void joinStory() {
        if (this.calculation) {
            System.out.println("Персонаж '" + getName() + "' присоединился к истории и его расчеты оказались верными");
        } else {
            System.out.println("Персонаж '" + getName() + "' присоединился к истории и с его расчетами что-то не так");
        }

    }

    public void changeLocation(Observable loc) {
        this.location = loc;
    }

    @Override
    public void walkBy(WalkablePlace place) {
        System.out.println(getName() + " начал гулять в месте: '" + place.getName() + "'");
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
        Guy guy = (Guy) obj;

        return getName().equals(guy.getName()) &&
                location.equals(guy.location) &&
                calculation == guy.calculation;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(calculation, location);
    }

    @Override
    public String toString() {
        if (location != null) {
            return "Character '" + getName() + "', now in: '" + location.getName() + "'";
        }
        return "Character '" + getName() + "'";
    }
}
