package org.example.data

import kotlin.math.pow

interface Shape {
    fun isInMyJurisdiction(xP: Int, yP: Int): Boolean
}

class Circle(private val x0: Int, private val y0: Int, private val radius: Int) : Shape {
    override fun isInMyJurisdiction(x: Int, y: Int): Boolean {
        return (x0 - x).toDouble().pow(2) + (y0 - y).toDouble().pow(2) <= radius.toDouble().pow(2)
    }
}

class Triangle(
    private val x1: Int,
    private val y1: Int,
    private val x2: Int,
    private val y2: Int,
    private val x3: Int,
    private val y3: Int
) : Shape {
    override fun isInMyJurisdiction(xP: Int, yP: Int): Boolean {
        val vect1 = getVect(x1 - xP, y1 - yP, x2 - x1, y2 - y1);
        val vect2 = getVect(x2 - xP, y2 - yP, x3 - x2, y3 - y2);
        val vect3 = getVect(x3 - xP, y3 - yP, x1 - x3, y1 - y3);
        return ((vect1 <= 0 && vect2 <= 0 && vect3 <= 0) or (vect1 >= 0 && vect2 >= 0 && vect3 >= 0))
    }

    private fun getVect(xA: Int, yA: Int, xB: Int, yB: Int): Int {
        return xA * yB - yA * xB
    }
}

class Tetragon(
    private val x1: Int,
    private val y1: Int,
    private val x2: Int,
    private val y2: Int,
    private val x3: Int,
    private val y3: Int,
    private val x4: Int,
    private val y4: Int
) : Shape {
    override fun isInMyJurisdiction(xP: Int, yP: Int): Boolean {
        if (Triangle(x1, y1, x2, y2, x3, y3).isInMyJurisdiction(xP, yP)) return true
        return Triangle(x1, y1, x3, y3, x4, y4).isInMyJurisdiction(xP, yP)
    }
}
