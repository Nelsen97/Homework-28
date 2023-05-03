package nail;

import java.util.Random;

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
        System.out.println("Ровная дорога. Возможно увеличение скорости движения.");
        if (merchant.getSpeed() < 5) {
            merchant.setSpeed(merchant.getSpeed() + 2);
            System.out.println("Скорость движения торговца стала = " + merchant.getSpeed());
        } else if (merchant.getSpeed() == 4) {
            System.out.println("Скорость торговца увеличилась на единицу!");
            merchant.setSpeed(merchant.getSpeed() + 1);
        } else if (merchant.getSpeed() == 5) {
            System.out.println("Торговец теперь движется с максимально возможной скоростью!");
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
        System.out.println("Торговцу на пути встретились разбойники!");
        if (merchant.getMoney() >= 10 && merchant.getGoodsList().isEmpty()) {
            System.out.println("Торговец решил откупиться у разбойников деньгами. Он заплатил им 10 монет");
            merchant.setMoney(merchant.getMoney() - 10);
        }
        int goodsCounter = 0;
        int bestGoods = 0;
        int secondBestGoods = 0;
        if (merchant.getMoney() < 10) {
            while (goodsCounter <= 5) {
                for (int i = 0; i < merchant.getGoodsList().size(); i++) {
                    if (merchant.getGoodsList().get(i).getProductQuality().getValue() > 1) {
                        merchant.getGoodsList().remove(i);
                        bestGoods++;
                        goodsCounter++;
                    }
                    if (merchant.getGoodsList().get(i).getProductQuality().getValue() > 0.6
                            && merchant.getGoodsList().get(i).getProductQuality().getValue() == 0.6) {
                        merchant.getGoodsList().remove(i);
                        secondBestGoods++;
                    }
                }
            }
            if (bestGoods > 0) {
                System.out.println("Разбойники забрали самый лучший товар у торговца в количестве - " + bestGoods + " продуктов!");
            } else {
                System.out.println("Разбойники украли не самого лучшего товара в количестве - " + secondBestGoods + " штук");
            }
            System.out.println("Осталось товаров у торговца - " + merchant.getGoodsList().size());
        }
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
                while (merchant.getCarryingCapacity() <= 100){
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

