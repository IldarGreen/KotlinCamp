package org.example.data

abstract class Zone(val phoneNumber: String, val shape: Shape) {
    abstract val shapeName: String

    companion object {
        val basePhoneNumber = "8880123456"
    }

    fun isMyIncident(x: Int, y: Int): Boolean {
        return shape.isInMyJurisdiction(x, y)
    }
}

class Round(phoneNumber: String, val circle: Circle) : Zone(phoneNumber, circle) {
    override val shapeName: String
        get() = "Circle"
}

class Triangular(phoneNumber: String, val triangle: Triangle) : Zone(phoneNumber, triangle) {
    override val shapeName: String
        get() = "Triangle"
}

class Tetragonal(phoneNumber: String, val tetragon: Tetragon) : Zone(phoneNumber, tetragon) {
    override val shapeName: String
        get() = "Tetragon"
}