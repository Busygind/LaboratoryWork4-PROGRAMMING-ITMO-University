import entities.*;
import exceptions.*;
import utilities.*;

public class Main {
    public static void main(String[] args) {

        Sea sea = new Sea("Море");
        sea.createBay("Залив");
        Guy guy = new Guy("Чувак");

        Observable hill = new Observable() {
            @Override
            public void observedBy(Person person) {
                System.out.println("Персонаж" + person.getName() + " любуется холмами");
            }

            @Override
            public String getName() {
                return "Холмы";
            }
        };
        guy.changeLocation(hill); //Типо на холмы пришел

        City lp = new City("Лос-Паганос");

        Rich[] riches = new Rich[]{new Rich("1"),
                new Rich("2"),
                new Rich("3"),
                new Rich("4"),
                new Rich("5"),
                new Rich("6"),
                new Rich("7"),
                new Rich("8"),
                new Rich("9"),
                new Rich("10"),
                new Rich("11"),
                new Rich("12")};
        for (Rich rich : riches) {
            if (lp.getWeather()) {
                try {
                    rich.setHome(lp);
                } catch (ToManyRichesException e) {
                    System.out.println("В этом городе уже хватает богачей");
                    break;
                }
            }
            lp.setWeather();
        }

        Street street = new Street("Самая большая и красивая ", lp, true);
        street.drag();

        Institution[] institutions = new Institution[]{new Institution("магазины", StreetSideType.RIGHT_SIDE),
                new Institution("рестораны", StreetSideType.RIGHT_SIDE),
                new Institution("столовые", StreetSideType.RIGHT_SIDE),
                new Institution("гостиницы", StreetSideType.RIGHT_SIDE),
                new Institution("кинотеатры", StreetSideType.RIGHT_SIDE),
                new Institution("весёлые балаганчики", StreetSideType.RIGHT_SIDE),
                new Institution("подземные гаражи", StreetSideType.RIGHT_SIDE),
                new Institution("бензозаправочные станции", StreetSideType.RIGHT_SIDE)};
        House house = new House();
        house.fillHouse(institutions);
        house.showHouseContent();

        Walker shorty = new Walker("Коротышка");
        Infrastructure[] infrastructure = new Infrastructure[]{new Infrastructure("Пляжи"),
                new Infrastructure("купальни"),
                new Infrastructure("ныряльные вышки"),
                new Infrastructure("лодочные и пароходные пристани"),
                new Infrastructure("плавучие рестораны"),
                new Infrastructure("морские качели и карусели"),
                new Infrastructure("чертовы водяные колеса"),
                new Infrastructure("параболоиды")};
        for (Infrastructure inf : infrastructure) {
            shorty.walkBy(inf);
            shorty.stopWalking(inf);
        }

        CommonRestaurant cr = new CommonRestaurant("Обычный ресторан", StreetSideType.LEFT_SIDE);
        FoodStation fs = new FoodStation(StreetSideType.LEFT_SIDE);

        MainCharacter ponchik = new MainCharacter("Пончик");
        Infrastructure shore = new Infrastructure("побережье");
        ponchik.walkBy(shore);
        ponchik.stopNearTheRestaurant(fs);
        try {
            ponchik.getHome();
        } catch (HasNotHomeException e) {
            System.out.println(e.getMessage());
        }

        MainCharacter waiter = new MainCharacter("Официант");
        cr.getTerraceAvailability();
        fs.getTerraceAvailability();
        fs.getOutsideServiceAvialability(waiter);

    }
}
