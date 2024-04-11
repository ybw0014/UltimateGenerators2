package net.guizhanss.ultimategenerators2.utils

import java.util.concurrent.ThreadLocalRandom

object RandomUtils {
    @JvmStatic
    fun nextBoolean(): Boolean {
        return ThreadLocalRandom.current().nextBoolean()
    }

    @JvmStatic
    fun nextFloat(origin: Float, bound: Float): Float {
        return ThreadLocalRandom.current().nextFloat(origin, bound)
    }
}
