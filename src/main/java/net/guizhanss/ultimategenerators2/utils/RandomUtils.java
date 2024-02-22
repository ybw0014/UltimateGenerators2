package net.guizhanss.ultimategenerators2.utils;

import java.util.concurrent.ThreadLocalRandom;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class RandomUtils {
    public static boolean nextBoolean() {
        return ThreadLocalRandom.current().nextBoolean();
    }

    public static float nextFloat(float origin, float bound) {
        return ThreadLocalRandom.current().nextFloat(origin, bound);
    }
}
