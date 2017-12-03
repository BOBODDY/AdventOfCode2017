package day3

import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.sqrt

val input = 347991

fun main(args: Array<String>) {
    print(calculateManhattanDistance(input))
}

fun calculateManhattanDistance(point: Int): Int {
    val n = 600

    val center = n / 2

    val coord = findCoordinates(point)

    return (abs(coord.first - 0) + abs(coord.second - 0))
}

fun findCoordinates( n: Int): Pair<Int, Int> {
    val k = ceil((sqrt(n.toDouble()) - 1) / 2)
    var t = 2 * k + 1
    var m = t * t

    t -= 1

    if (n >= m - t) {
        val x = k - (m - n)
        val y = -k
        return Pair(x.toInt(), y.toInt())
    }

    m -= t

    if(n >= m - t) {
        val x = -k
        val y = -k + (m - n)
        return Pair(x.toInt(), y.toInt())
    }

    m -= t

    if(n >= m - t) {
        val x = -k + (m - n)
        val y = k
        return Pair(x.toInt(), y.toInt())
    }

    val x = k
    val y = k - (m - n - t)
    return Pair(x.toInt(), y.toInt())
}