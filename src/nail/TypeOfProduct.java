package nail;

import java.util.Random;

public enum TypeOfProduct {

    MEAT("Мясо", 1),
    DRIED_FRUITS("Сухофрукты", 1),
    GRAIN("Зерно", 5),
    FLOUR("Мука", 3),
    FABRICS("Ткани", 10),
    PAINT("Краска", 2);

    private final String name;
    private final Integer weight;

    TypeOfProduct(String name, Integer weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public Integer getWeight() {
        return weight;
    }

    public static TypeOfProduct getRandomType() {
        Random random = new Random();
        int choice = random.nextInt(6) + 1;
        switch (choice) {
            case 2:
                return TypeOfProduct.DRIED_FRUITS;
            case 3:
                return TypeOfProduct.GRAIN;
            case 4:
                return TypeOfProduct.FLOUR;
            case 5:
                return TypeOfProduct.FABRICS;
            case 6:
                return TypeOfProduct.PAINT;
            case 1:
            default: return TypeOfProduct.MEAT;
        }
    }
}
