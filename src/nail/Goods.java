package nail;

import java.util.Comparator;
import java.util.Random;

import static nail.ProductQuality.*;

public class Goods implements Comparator<Goods> {
    private TypeOfProduct typeOfProduct;
    private ProductQuality productQuality;
    private Double price;
    private Integer weight;
    private final Random random = new Random();

    public Goods(TypeOfProduct typeOfProduct, ProductQuality productQuality, Double price, Integer weight) {
        this.typeOfProduct = typeOfProduct;
        this.productQuality = productQuality;
        this.price = price;
        this.weight = weight;
    }

    public Goods() {
    }

    public Double getPriceOfProduct() {
        int price = random.nextInt(5) + 1;
        double costOfGoods = 0.0;
        ProductQuality productQuality = getRandomProductQuality();
        switch (productQuality) {
            case DAMAGED_IN_THE_TRASH:
                costOfGoods = price * 0.1;
                break;
            case ALMOST_ALL_GONE:
                costOfGoods = price * 0.25;
                break;
            case HALF_IS_RUINED:
                costOfGoods = price * 0.55;
                break;
            case SLIGHTLY_SPOILED:
                costOfGoods = price * 0.95;
                break;
            case NORMAL:
                costOfGoods = price * 1.2;
                break;
        }

        TypeOfProduct typeOfProduct = TypeOfProduct.MEAT;
        switch (typeOfProduct) {
            case MEAT:
                return Math.round((costOfGoods + 2.5) * 100.0) / 100.0;
            case FLOUR:
                return Math.round((costOfGoods + 0.8) * 100.0) / 100.0;
            case GRAIN:
                return Math.round((costOfGoods + 0.6) * 100.0) / 100.0;
            case PAINT:
                return Math.round((costOfGoods + 1.0) * 100.0) / 100.0;
            case FABRICS:
                return Math.round((costOfGoods + 2.0) * 100.0) / 100.0;
            case DRIED_FRUITS:
            default:
                return Math.round((costOfGoods + 0.5) * 100.0) / 100.0;
        }
    }

    public Goods getRandomGood() {
        TypeOfProduct typeOfProduct = TypeOfProduct.getRandomType();
        ProductQuality productQuality = getRandomProductQuality();
        Double costOfGoods = getPriceOfProduct();
        return new Goods(typeOfProduct, productQuality, Math.round((costOfGoods) * 100.0) / 100.0, typeOfProduct.getWeight());
    }

    public Goods getRandomGood(City city) {
        TypeOfProduct typeOfProduct = TypeOfProduct.getRandomType();
        ProductQuality productQuality = getRandomProductQuality();
        Double costOfGoods = getPriceOfProduct() * city.getCostFactor();
        return new Goods(typeOfProduct, productQuality, Math.round((costOfGoods) * 100.0) / 100.0, typeOfProduct.getWeight());
    }

    public ProductQuality changeQualityOfProduct(Goods goods) {
        switch (goods.getProductQuality()) {
            case NORMAL:
                goods.setProductQuality(SLIGHTLY_SPOILED);
                System.out.println("Качество товара стало слегка испорченным.");
                return goods.getProductQuality();
            case SLIGHTLY_SPOILED:
                goods.setProductQuality(HALF_IS_RUINED);
                System.out.println("Товар испортился на половину.");
                return goods.getProductQuality();
            case HALF_IS_RUINED:
                goods.setProductQuality(ALMOST_ALL_GONE);
                System.out.println("Товар почти весь пропал.");
                return goods.getProductQuality();
            case ALMOST_ALL_GONE:
                goods.setProductQuality(DAMAGED_IN_THE_TRASH);
                System.out.println("Товар теперь испорчен в хлам.");
                return goods.getProductQuality();
            case DAMAGED_IN_THE_TRASH:
                System.out.println("Товар итак испорчен в хлам, хуже не стало. :)");
                return goods.getProductQuality();
        }
        return goods.getProductQuality();
    }

    public ProductQuality getProductQuality() {
        return productQuality;
    }

    public void setProductQuality(ProductQuality productQuality) {
        this.productQuality = productQuality;
    }

    public TypeOfProduct getTypeOfProduct() {
        return typeOfProduct;
    }

    public void setTypeOfProduct(TypeOfProduct typeOfProduct) {
        this.typeOfProduct = typeOfProduct;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return typeOfProduct.getName() +
                ", Качество = " + productQuality.getValue() +
                ", Цена = " + price +
                ", Вес = " + weight + "\n";
    }

    @Override
    public int compare(Goods g1, Goods g2) {
        return Double.compare(g2.getProductQuality().getValue(), g1.getProductQuality().getValue());
    }
}
