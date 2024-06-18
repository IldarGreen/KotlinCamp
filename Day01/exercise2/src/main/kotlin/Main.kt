package org.example

import org.example.data.*
import org.example.extensions.phoneMask
import org.example.utils.*

fun main() {
    try {
        println("Enter zone parameters:")

        val zoneParamInput = readlnOrNull() ?: ""
        val zonePhoneNumber = "89198765432"
        val zone = getZoneByParamINputAndPhoneNumber(zoneParamInput, zonePhoneNumber)

        println("\nThe zone info:")
        println("\tThe shape of zone: ${zone.shapeName}")
        println("\tPhone number: ${zone.phoneNumber.phoneMask()}")

        println("\nEnter an incident coordinates:")
    //    9;9

        val incidentCoordsStr = readlnOrNull() ?: ""
        val incidentCoordsStrList = incidentCoordsStr.split(';')
        if (incidentCoordsStrList.size != 2) {
            throw Exception("Incorrect set of parameters")
        }
        val incidentX = incidentCoordsStrList[0].toInt()
        val incidentY = incidentCoordsStrList[1].toInt()

        val incident = Incident(incidentX, incidentY, descriptions.random(), phones.random(), null)
        incident.type = IncidentType.PRINCES

        println("\nThe incident info:")
        println("\tDescription: ${incident.description}")
        incident.applicantPhoneNumber?.let {
            println("\tPhone number: ${it.phoneMask()}")
        }
        incident.type?.let {
            println("\tType: ${it.type}")
        }
        println()

        if (zone.isMyIncident(incident.x, incident.y)) {
            println("An incident is in zone")
        } else {
            println("An incident is not in zone")
            println("Switch the applicant to the common number: ${Zone.basePhoneNumber.phoneMask()}")
        }
    } catch(ex: Exception) {
        println("Error: " + ex.message)
    }

}

fun getZoneByParamINputAndPhoneNumber(zoneParamInput: String, zonePhoneNumber: String): Zone {
    val zoneParamList = zoneParamInput.split(' ')
    return when (zoneParamList.size) {
        2 -> {
            val (zoneCenterX, zoneCenterY) = zoneParamList[0].split(';')
            val zoneRadius = zoneParamList[1].toInt()
            Round(zonePhoneNumber, Circle(zoneCenterX.toInt(), zoneCenterY.toInt(), zoneRadius))
        }

        3 -> {
            val (zoneX1, zoneY1) = zoneParamList[0].split(';')
            val (zoneX2, zoneY2) = zoneParamList[1].split(';')
            val (zoneX3, zoneY3) = zoneParamList[2].split(';')
            Triangular(zonePhoneNumber, Triangle(zoneX1.toInt(), zoneY1.toInt(),
                                                zoneX2.toInt(), zoneY2.toInt(),
                                                zoneX3.toInt(), zoneY3.toInt()))
        }

        4 -> {
            val (zoneX1, zoneY1) = zoneParamList[0].split(';')
            val (zoneX2, zoneY2) = zoneParamList[1].split(';')
            val (zoneX3, zoneY3) = zoneParamList[2].split(';')
            val (zoneX4, zoneY4) = zoneParamList[3].split(';')
            Tetragonal(zonePhoneNumber, Tetragon(zoneX1.toInt(), zoneY1.toInt(),
                                                zoneX2.toInt(), zoneY2.toInt(),
                                                zoneX3.toInt(), zoneY3.toInt(),
                                                zoneX4.toInt(), zoneY4.toInt()))
        }

        else -> {
            throw Exception("Incorrect set of parameters")
        }
    }
}

//    circle 5;4 6
//    triangle 5;4 3;6 2;5
//    tetragon 5;4 3;6 2;5 5;9

//kotlinc  -include-runtime -d main.jar
//java -jar main.jar -param lower