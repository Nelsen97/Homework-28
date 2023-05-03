package nail;

import java.util.Random;

public enum ProductQuality {
    NORMAL("Нормальное", 1.2),
    SLIGHTLY_SPOILED("Слегка испорчен", 0.95),
    HALF_IS_RUINED("Половина испортилась", 0.55),
    ALMOST_ALL_GONE("Почти весь пропал", 0.25),
    DAMAGED_IN_THE_TRASH("Испорчен в хлам", 0.1);

    private final String description;
    private final Double value;


    ProductQuality(String description, Double value) {
        this.description = description;
        this.value = value;
    }

    public static ProductQuality getRandomProductQuality() {
        Random random = new Random();
        int choice = random.nextInt(5) + 1;
        switch (choice) {
            case 1:
            default: return ProductQuality.NORMAL;
            case 2:
                return ProductQuality.SLIGHTLY_SPOILED;
            case 3:
                return ProductQuality.HALF_IS_RUINED;
            case 4:
                return ProductQuality.ALMOST_ALL_GONE;
            case 5:
                return ProductQuality.DAMAGED_IN_THE_TRASH;
        }
    }

    public String getDescription() {
        return description;
    }

    public Double getValue() {
        return value;
    }
}
