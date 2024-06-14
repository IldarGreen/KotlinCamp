package org.example

import kotlin.math.abs
import kotlin.math.pow
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

    checkCircles(x1, y1, r1, x2, y2, r2)
}

fun input(): Double {
    var x: Double? = null
    while (x == null) {
        try {
            x = readln().toDouble()
        } catch (e: NumberFormatException) {
            println("Couldn't parse a number. Please, try again")
        }
    }
    return x
}

fun checkCircles(x1: Double, y1: Double, r1: Double, x2: Double, y2: Double, r2: Double) {
    if (x1 == x2 && y1 == y2) {
        val result = if (r1 == r2) "coincide" else "one inside another"
        println("Result: the circles $result")
    } else {
        val katetX = x2 - x1
        val katetY = y2 - y1
        val hypotenuse = sqrt(katetX.pow(2) + katetY.pow(2))

        if (hypotenuse.eq(r1 + r2)) {
            println("Result: the circles touch")
            printIntersectPoint(x1, y1, r1, x2, y2, r2)
        }
        else if (hypotenuse.less(r1 + r2)) {
            println("Result: the circles intersect")
            printIntersectPoint(x1, y1, r1, x2, y2, r2)
        } else {
            println("Result: the circles not intersect")
        }
    }
}

fun Double.eq(other: Double, epsilon: Double = 0.00000001) = Math.abs(this - other) < epsilon

fun Double.less(other: Double, epsilon: Double = 0.00000001) = this - other < epsilon

fun printIntersectPoint(x1: Double, y1: Double, r1: Double, x2: Double, y2: Double, r2: Double) {
    val epsilon = 0.00000001
    val d: Double = sqrt((x1 - x2).pow(2) + (y1 - y2).pow(2))
    if (abs(d - (r1 + r2)) < epsilon) {
        val x3 = (x1 + x2) / 2
        val y3 = (y1 + y2) / 2
        println("Окружности касаются в точке: x = $x3 y = $y3")
    } else {
        val b = (r2.pow(2) - r1.pow(2) + d.pow(2)) / (2 * d)
        val a = d - b
        val h = sqrt(r2.pow(2) - b.pow(2))
        val x = x1 + (x2 - x1) / (d / a)
        val y = y1 + (y2 - y1) / (d / a)
        val x3 = x - (y - y2) * h / b
        val y3 = y + (x - x2) * h / b
        val x4 = x + (y - y2) * h / b
        val y4 = y - (x - x2) * h / b
        println("Точки пересечения: A: x = ${"%.2f".format(x3)} y = ${"%.2f".format(y3)}")
        println("                   B: x = ${"%.2f".format(x4)} y = ${"%.2f".format(y4)}")
    }
}
