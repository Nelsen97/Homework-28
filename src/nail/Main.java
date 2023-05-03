package nail;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) {
        try {
            StartingConditions startingConditions = new StartingConditions();
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
