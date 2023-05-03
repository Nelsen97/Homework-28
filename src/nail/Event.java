package nail;

import java.util.*;

public class Event implements RandomEvents {
    private final Random random = new Random();

    public Event() {
    }

    @Override
    public void casualDay() {
        System.out.println("Сегодня был обычный день :)");
    }

    @Override
    public void rain(Goods goods, Merchant merchant) {
        System.out.println("Идет дождь. Скорость передвижения снижена на 2 лиги.");
        merchant.setSpeed(merchant.getSpeed() - 2);
        int chance = random.nextInt(3) + 1;
        if (chance == 3) {
            System.out.print("\nИз-за дождя был поврежден товар: " + goods);
            goods.setProductQuality(goods.changeQualityOfProduct(goods));
            System.out.println("Теперь его качество стало:" + goods.getProductQuality().getDescription());
        }
    }

    @Override
    public void smoothRoad(Merchant merchant) {
        System.out.println("Ровная дорога. Скорость движения торговца увеличилась!");
        if (merchant.getSpeed() < 5) {
            merchant.setSpeed(merchant.getSpeed() + 2);
            System.out.println("Скорость движения торговца стала = " + merchant.getSpeed());
        } else if (merchant.getSpeed() == 4) {
            System.out.println("Скорость торговца увеличилась на единицу!");
            merchant.setSpeed(merchant.getSpeed() + 1);
        } else if (merchant.getSpeed() == 5) {
            System.out.println("Торговец теперь движется с максимально возможной скоростью!");
            merchant.setSpeed(5);
        }
    }

    @Override
    public void brokenWheel() {
        System.out.println("Сломалось колесо! День потрачен в пустую :(");
    }

    @Override
    public void metALocal(Merchant merchant) {
        System.out.print("\nПо пути торговец встретил местного жителя. Благодаря этому вы срезали путь на ");
        int chance = random.nextInt(4) + 1;
        if (chance == 1) {
            System.out.println("3 лиги.");
            merchant.setDistance(merchant.getDistance() + 3);
        }
        if (chance == 2) {
            System.out.println("4 лиги.");
            merchant.setDistance(merchant.getDistance() + 4);
        }
        if (chance == 3) {
            System.out.println("5 лиг.");
            merchant.setDistance(merchant.getDistance() + 5);
        }
        if (chance == 4) {
            System.out.println("6 лиг.");
            merchant.setDistance(merchant.getDistance() + 6);
        }
    }

    @Override
    public void highwayRobbers(Merchant merchant) {
        int stolenGoodsCount = 0;
        System.out.println("Торговцу на пути встретились разбойники!");
        if (merchant.getMoney() >= 10) {
            System.out.println("Торговец решил откупиться у разбойников деньгами. Он заплатил им 10 монет");
            System.out.println("У торговца осталось денег: " + merchant.getMoney());
            merchant.setMoney(merchant.getMoney() - 10);
        } else if (merchant.getMoney() < 10) {
            System.out.println("У торговца не осталось денег для выплаты разбойникам, они заберут у него товары.");
            int goodsToRemove = Math.min(5, merchant.getGoodsList().size());
            List<Goods> sortedGoods = new ArrayList<>(merchant.getGoodsList());
            Collections.sort(sortedGoods, Comparator.comparingDouble(g -> -g.getProductQuality().getValue()));
            List<Goods> goodsToRemoveList = sortedGoods.subList(0, goodsToRemove);

            int totalPrice = 0;
            for (Goods goods : goodsToRemoveList) {
                totalPrice += goods.getPrice();
                merchant.getGoodsList().remove(goods);
            }
            System.out.println("У торговца забрали: " + goodsToRemove + " товаров.");
        }
        System.out.println("Осталось товаров у торговца - " + merchant.getGoodsList().size());
    }

    @Override
    public void roadSideInn(Merchant merchant) {
        int choice = random.nextInt(2) + 1;
        System.out.println("Торговец остановился у придорожного трактира и проведет там ночь. Стоимость ночлега 2 золотых");
        merchant.setMoney(merchant.getMoney() - 2);
        TypeOfProduct typeOfProduct = TypeOfProduct.getRandomType();

        if (choice == 1) {
            if (merchant.getCarryingCapacity() < 100) {
                System.out.println("Он решил, что стоит потратить часть денег на товары в трактире.");
                while (merchant.getCarryingCapacity() <= 100) {
                    merchant.getGoodsList().add(new Goods().getRandomGood());
                }
            } else {
                System.out.println("Торговец мог бы купить товары в трактире, но его тележка переполнена (");
            }
        }
        if (choice == 2) {
            
        }
    }

    @Override
    public void productIsDamaged(Goods goods) {

    }
}

