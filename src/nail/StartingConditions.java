package nail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StartingConditions {
    private Merchant merchant = new Merchant();
    private Goods goods = new Goods();
    private List<City> cities = new ArrayList<>();
    private final Random random = new Random();
    Event event = new Event();

    public StartingConditions() {
        initiateCitiesList();
        City.getCityInformation(cities);
        merchant.merchantBuysGoods(cities.get(0));

        int dayCounter = 0;

        City chosenCity = cities.get(chooseRandomCity());
        System.out.println("\n\nТорговец решил поехать в город: " + chosenCity.getName()
                + ". Расстояние до города: " + chosenCity.getDistance());

        while (merchant.getDistance() < chosenCity.getDistance()) {
            dayCounter++;
            System.out.println("День " + dayCounter);
            int value = random.nextInt(merchant.getGoodsList().size()) + 1;
            event.rain(merchant.getGoodsList().get(value - 1), merchant);
            merchantStartGoing(chosenCity);
            if (merchant.getDistance() > chosenCity.getDistance()) {
                merchant.setDistance(0);
                break;
            }
        }
    }

    public void initiateEvent() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Method> functions = Arrays.asList(
                event.getClass().getMethod("casualDay"),
                event.getClass().getMethod("rain", Goods.class, Merchant.class),
                event.getClass().getMethod("smoothRoad", Merchant.class),
                event.getClass().getMethod("brokenWheel"),
                event.getClass().getMethod("metALocal", Merchant.class),
                event.getClass().getMethod("highwayRobbers", Merchant.class),
                event.getClass().getMethod("roadSideInn", Merchant.class),
                event.getClass().getMethod("productIsDamaged", Goods.class)
        );

        int randomIndex = random.nextInt(functions.size());
        Method randomFunction = functions.get(randomIndex);
        Object[] arguments = null;

        if (randomFunction.getParameterCount() > 0) {
            arguments = new Object[randomFunction.getParameterCount()];
            for (int i = 0; i < arguments.length; i++) {
                if (randomFunction.getParameterTypes()[i] == Goods.class) {
                    arguments[i] = goods;
                } else if (randomFunction.getParameterTypes()[i] == Merchant.class) {
                    arguments[i] = merchant;
                }
            }
        }
        randomFunction.invoke(event, arguments);
    }

    public void initiateCitiesList() {
        cities.add(new City("Каракол"));
        cities.add(new City("Ош"));
        cities.add(new City("Бишкек"));
        cities.add(new City("Талас"));
        cities.add(new City("Нарын"));
        System.out.println("Торговец начинает в городе " + cities.get(0).getName()
                + ". Стоимость товаров в городе: " + cities.get(0).getCostFactor());
    }

    public int chooseRandomCity() {
        int choice = random.nextInt(4) + 1;
        switch (choice) {
            case 1:
            default:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
        }
    }

    public void merchantStartGoing(City city) {
        if (merchant.getSpeed() == 0 || merchant.getSpeed() < 0) {
            merchant.setSpeed(1);
        }
        System.out.println("Скорость движения торговца на тележке = " + merchant.getSpeed());
        int way = merchant.getDistance();
        way += merchant.getSpeed();
        merchant.setDistance(way);
        if (merchant.getDistance() >= city.getDistance()) {
            System.out.println("Торговец доехал до города " + city.getName());
        } else {
            System.out.println("Торговец проехал на тележке: " + way + ". Осталось проехать еще: " + (city.getDistance() - way));
            merchant.setSpeed(random.nextInt(5) + 1);
        }
    }
}
