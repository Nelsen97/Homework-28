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
    private List<Method> functions = Arrays.asList(
            event.getClass().getMethod("casualDay"),
            event.getClass().getMethod("rain", Merchant.class),
            event.getClass().getMethod("smoothRoad", Merchant.class),
            event.getClass().getMethod("brokenWheel"),
            event.getClass().getMethod("metALocal", Merchant.class),
            event.getClass().getMethod("highwayRobbers", Merchant.class),
            event.getClass().getMethod("roadSideInn", Merchant.class),
            event.getClass().getMethod("productIsDamaged", Merchant.class)
    );

    public StartingConditions() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        initiateCitiesList();
        City.getCityInformation(cities);
        double costs = 0;
        costs = merchant.merchantBuysGoods(cities.get(0));
        City chosenCity = cities.get(chooseRandomCity());
        System.out.println("\n\nТорговец решил поехать в город: " + chosenCity.getName()
                + ". Расстояние до города: " + chosenCity.getDistance());
        motionSimulation(chosenCity);
        double profit = merchant.merchantSalesHisGoodsInCity(chosenCity);
        if (costs > profit) {
            System.out.printf("Торговля была неудачной. Торговец потерял на продаже товаров: %.2f - %.2f = %.2f",
                    costs, profit, (costs - profit));
        } else if (profit > costs) {
            System.out.printf("Торговец вышел в прибыль! Он заработал: %.2f - %.2f = %.2f",
                    profit, costs, (profit - costs));
        } else {
            System.out.printf("Торговец вышел в ноль! Он купил товары на сумму: %.2f и продал на сумму: %.2f", costs,profit);
        }
    }

    public void motionSimulation(City chosenCity) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        int dayCounter = 0;

        while (merchant.getDistance() < chosenCity.getDistance()) {
            dayCounter++;
            System.out.println("День " + dayCounter + "\n");
//            initiateEvent(chosenCity);
            int index = random.nextInt(8) + 1;
            startRandomEvent(index);
            if (index != 4) {
                merchantStartGoing(chosenCity);
            }
//            event.roadSideInn(merchant);
//            event.productIsDamaged(merchant);
            int counter = 0;
            if (merchant.getDistance() >= chosenCity.getDistance()) {
                merchant.setDistance(0);
                break;
            }
        }
    }

//    public void saleGoodsInChosenCity(City city) {
//        double profit = merchant.merchantSalesHisGoodsInCity();
//    }

    public void startRandomEvent(int indexOfEvent) {
        switch (indexOfEvent) {
            case 1:
                event.casualDay();
                break;
            case 2:
                event.rain(merchant);
                break;
            case 3:
                event.smoothRoad(merchant);
                break;
            case 4:
                event.brokenWheel();
                break;
            case 5:
                event.metALocal(merchant);
                break;
            case 6:
                event.highwayRobbers(merchant);
                break;
            case 7:
                event.roadSideInn(merchant);
                break;
            case 8:
                event.productIsDamaged(merchant);
                break;
        }
    }

    public void initiateEvent(City chosenCity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
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

        try {
            randomFunction.invoke(event, arguments);
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof NoSuchMethodException) {
                // Обработка отсутствия метода
                System.out.println("Метод не найден");
            } else {
                // Обработка других исключений
                e.printStackTrace();
            }
        } catch (Exception e) {
            // Обработка других исключений
            e.printStackTrace();
        }

        try {
            Method merchantStartGoing = getClass().getMethod("merchantStartGoing", City.class);
            merchantStartGoing.invoke(this, chosenCity);
        } catch (NoSuchMethodException e) {
            // Обработка отсутствия метода
            System.out.println("Метод merchantStartGoing не найден");
        } catch (Exception e) {
            // Обработка других исключений
            e.printStackTrace();
        }
    }

    public void initiateCitiesList() {
        cities.add(new City("Бишкек"));
        cities.add(new City("Ош"));
        cities.add(new City("Каракол"));
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
            System.out.println("Торговец проехал: " + way + ". Осталось проехать еще: " + (city.getDistance() - way));
            merchant.setSpeed(random.nextInt(5) + 1);
        }
    }
}
