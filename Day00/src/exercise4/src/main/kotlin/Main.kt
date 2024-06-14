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
    val season = inputSeason()

    println("Season: $season. Enter a temperature (use Celsius):")
    var temperature = inputDouble()
    temperature = convertTemperature(param, temperature)

    println("Enter humidity:")
    var humidity = inputInt()

    println()
    temperatureInfo(temperature, param, season)
    println()
    humidityInfo(humidity, season)
}

fun inputSeason(): String {
    var run = true
    var season = ""
    while (run) {
        season = readln()
        when (season) {
            "S", "s" -> {
                season = "Summer"
                run = false
            }
            "W", "w" -> {
                season = "Winter"
                run = false
            }
            else -> println("Incorrect parameter entered, Please, try again")
        }
    }
    return season
}

fun inputDouble(): Double {
    var x: Double? = null
    while(x == null) {
        try { x = readln().toDouble()
        } catch (e: NumberFormatException) {
            println("Couldn't parse a number. Please, try again")
        }
    }
    return x
}

fun inputInt(): Int {
    var x: Int? = null
    while(x == null) {
        try {
            x = readln().toInt()
            if (x < 0 || x > 100) {
                x = null
                println("The value is not valid, Please, try again")
            }
        } catch (e: NumberFormatException) {
            println("Couldn't parse a number. Please, try again")
        }
    }
    return x
}

fun temperatureInfo(temperature: Double, param: String, season: String) {
    println("The temperature is ${"%.2f".format(temperature)} ˚${param[0]}")
    val wLower = convertTemperature(param, 20.0)
    val wUpper = convertTemperature(param, 22.0)
    val sLower = convertTemperature(param, 22.0)
    val sUpper = convertTemperature(param, 25.0)
    when (season) {
        "Winter" -> {
            println("The comfortable temperature is from ${"%.2f".format(wLower)} to ${"%.2f".format(wUpper)} ˚${param[0]}.")
            when (temperature) {
                in wLower..wUpper -> println("The temperature is excellent.")
                in -2000.0..wLower-> println("Please, make it warmer by ${"%.2f".format(wLower - temperature)} degrees.")
                in wUpper..2000.0-> println("Please, make it cooler by ${"%.2f".format(temperature - wUpper)} degrees.")
            }
        }
        "Summer" -> {
            println("The comfortable temperature is from ${"%.2f".format(sLower)} to ${"%.2f".format(sUpper)} ˚${param[0]}.")
            when (temperature) {
                in sLower..sUpper -> println("The temperature is excellent.")
                in -2000.0..sLower -> println("Please, make it warmer by ${"%.2f".format(sLower - temperature)} degrees.")
                in sUpper..2000.0 -> println("Please, make it cooler by ${"%.2f".format(temperature - sUpper)} degrees.")
            }
        }
    }
}

fun humidityInfo(humidity: Int, season: String) {
    when (season) {
        "Winter" -> {
            println("The comfortable humidity is from 30% to 45%")
            when (humidity) {
                in 30..<45 -> println("The humidity is comfortable.")
                in 0..<30-> println("Please, increase humidity by ${30 - humidity}%.")
                in 45..100-> println("Please, reduce humidity by ${humidity - 45}%.")
            }
        }
        "Summer" -> {
            println("The comfortable humidity is from 30% to 60%")
            when (humidity) {
                in 30..<60 -> println("The humidity is comfortable.")
                in 0..30 -> println("Please, increase humidity by ${30 - humidity}%")
                in 60..<100 -> println("Please, reduce humidity by ${humidity - 60}%")
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
