package nail;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class City {
    private String name;
    private Integer distance;
    private Double costFactor;
    private List<Goods> goodsList = new ArrayList<>();
    private final Random random = new Random();

    public City(String name) {
        this.name = name;
        distance = random.nextInt(51) + 50;
        costFactor = Math.round((random.nextDouble() + 0.5) * 100.0) / 100.0;
//        System.out.println("Distance of city " + name + " = " + getDistance());
//        System.out.println("Cost factor of city " + name + " = " + getCostFactor());
    }

    public static void getCityInformation(List<City> cities) {
        for (int i = 1; i < cities.size(); i++) {
            System.out.println(cities.get(i).name + " Расстояние: " + cities.get(i).getDistance() + " лиги. \tСтоимость товаров: "
                    + cities.get(i).getCostFactor());
        }
    }

    public Double getCostFactor() {
        return costFactor;
    }

    public String getName() {
        return name;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
}
