package challenge;

import java.util.List;
import java.util.Random;

public class Util {

    public static <T> T getRandomElement(List<T> t) {
        return t.get(new Random().nextInt(t.size()));
    }
}
