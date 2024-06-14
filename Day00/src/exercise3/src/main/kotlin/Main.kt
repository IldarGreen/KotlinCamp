package org.example

fun main(args: Array<String>) {
    val param = if (args.contains("-param")) {
        try {
            args[1 + args.indexOf("-param")]
        } catch (e:IndexOutOfBoundsException) {
            "Celsius"
        }
    } else "Celsius"

    if ((param != "Kelvin") and (param != "Fahrenheit") and (param != "Celsius")) {
        println("No argument given")
        System.exit(0)
    }
    println("Output mode: $param")
    println("Enter a season - (W)inter or (S)ummer:")
    var season = readln()
    when (season) {
        "S", "s" -> season = "Summer"
        "W", "w" -> season = "Winter"
        else -> {
            println("Incorrect parameter entered, bye bye")
            System.exit(0)
        }
    }
    println("Season: $season. Enter a temperature (use Celsius):")
    var temperature = readln().toDouble()
    temperature = convertTemperature(param, temperature)

    println()
    println("The temperature is ${"%.2f".format(temperature)} ˚${param[0]}")
    val wLower = convertTemperature(param, 20.0)
    val wUpper = convertTemperature(param, 22.0)
    val sLower = convertTemperature(param, 22.0)
    val sUpper = convertTemperature(param, 25.0)
    when (season) {
        "Winter" -> {
            println("The comfortable temperature is from ${"%.2f".format(wLower)} to ${"%.2f".format(wUpper)} ˚${param[0]}.")
            when (temperature) {
                in wLower..wUpper -> println("Еhe temperature is excellent.")
                in -2000.0..wLower-> println("Please, make it warmer by ${"%.2f".format(wLower - temperature)} degrees.")
                in wUpper..2000.0-> println("Please, make it cooler by ${"%.2f".format(temperature - wUpper)} degrees.")
            }
        }
        "Summer" -> {
            println("The comfortable temperature is from ${"%.2f".format(sLower)} to ${"%.2f".format(sUpper)} ˚${param[0]}.")
            when (temperature) {
                in sLower..sUpper -> println("Еhe temperature is excellent.")
                in -2000.0..sLower -> println("Please, make it warmer by ${"%.2f".format(sLower - temperature)} degrees.")
                in sUpper..2000.0 -> println("Please, make it cooler by ${"%.2f".format(temperature - sUpper)} degrees.")
            }
        }
    }

}

fun convertTemperature(param: String, temperature: Double): Double {
    if (param == "Kelvin")
        return temperature + 273.15
    if (param == "Fahrenheit") {
        return temperature * 1.8 + 32
    }
    return temperature
}

////kotlinc Main.kt -include-runtime -d main.jar
////java -jar main.jar -param Celsius
////java -jar main.jar -param Kelvin
////java -jar main.jar -param Fahrenheit
