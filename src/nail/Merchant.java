package nail;

import java.util.*;

public class Merchant {
    private Double money;
    private Double startMoney;
    private Integer speed;
    private Integer carryingCapacity;
    private Integer distance;
    private List<Goods> goodsList = new ArrayList<>();
    Random random = new Random();

    public Merchant(Double money, Integer speed, Integer carryingCapacity, Integer distance) {
        this.money = money;
        this.speed = speed;
        this.carryingCapacity = carryingCapacity;
        this.distance = distance;
    }

    public Merchant() {
        money = 500.0;
        startMoney = 500.0;
        speed = random.nextInt(5) + 1;
        carryingCapacity = 250;
        distance = 0;
    }

//    public List<Goods> merchantSalesHisGoods(int quantity) {
//        for (int i = 0; i < quantity; i++) {
//
//        }
//    }

    public void merchantBuysGoods(City city) {
        List<Goods> firstCitiesGoodsList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            firstCitiesGoodsList.add(new Goods().getRandomGood(city));
        }
        while (getMoney() > firstCitiesGoodsList.get(getLowestCostIndex(firstCitiesGoodsList)).getPrice()
                || getCarryingCapacity() > 0) {
            Goods product = new Goods().getRandomGood(city);
            goodsList.add(product);
            setMoney(getMoney() - product.getPriceOfProduct());
            setCarryingCapacity(getCarryingCapacity() - product.getWeight());
            if (getMoney() <= 0) {
                setMoney(0.0);
                break;
            }
            if (getCarryingCapacity() <= 0) {
                setCarryingCapacity(0);
                break;
            }
        }
        System.out.println("Торговец закупил товары. Осталось денег = " + getMoney()
                + " Осталось места в тележке = " + getCarryingCapacity());
    }

    public int getLowestCostIndex(List<Goods> goodsList) {
        int price = 0;
        for (int i = 1; i < goodsList.size(); i++) {
            if (goodsList.get(i).getPrice() < goodsList.get(price).getPrice()) {
                price = i;
            }
        }
        return price;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Double getMoney() {
        return Math.round((money) * 100.0) / 100.0;
    }


    public Double getStartMoney() {
        return startMoney;
    }

    public void setMoney(Double money) {
        this.money = money;
    }


    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(Integer carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}
