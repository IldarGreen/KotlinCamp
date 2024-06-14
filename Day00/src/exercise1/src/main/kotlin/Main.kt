package org.example

import kotlin.math.sqrt

fun main() {
    println("Input x1:")
    val x1 = input()
    println("Input y1:")
    val y1 = input()
    println("Input r1:")
    val r1 = input()
    println("Input x2:")
    val x2 = input()
    println("Input y2:")
    val y2 = input()
    println("Input r2:")
    val r2 = input()

    println(checkCircles(x1, y1, r1, x2, y2, r2))
}

fun input(): Double {
    var x: Double? = null
    while(x == null) {
        try { x = readln().toDouble()
        } catch (e: NumberFormatException) {
            println("Couldn't parse a number. Please, try again")
        }
    }
    return x
}

fun checkCircles(x1: Double?, y1: Double, r1: Double, x2: Double, y2: Double, r2: Double): String {
    var result = "not intersect"
    if (x1 == x2 && y1 == y2)
        result = if (r1 == r2) "coincide" else "one inside another"
    else {
        val katetX = x2 - x1!!
        val katetY = y2 - y1
        val hypotenuse = sqrt(katetX * katetX + katetY * katetY)

        if (hypotenuse.eq(r1 + r2)) result = "touch"
        else if (hypotenuse.less(r1 + r2)) result = "intersect"
    }

    return  "Result: the circles $result"
}

fun Double.eq(other: Double, epsilon: Double = 0.00000001) = Math.abs(this - other) < epsilon

fun Double.less(other: Double, epsilon: Double = 0.00000001) = this - other < epsilon
