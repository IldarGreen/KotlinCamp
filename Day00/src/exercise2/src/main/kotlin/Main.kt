package org.example

fun main(args: Array<String>) {
    var param = ""
    if (args.contains("-param")) {
        param = args[1 + args.indexOf("-param")]
        println("The grouping order is: $param")
    }
    else {
        println("Input parameter not specified")
        System.exit(0)///////////
    }

    println("Enter a number:")

    var num: Int? = null
    try { num = readln().toInt()
    } catch (e: NumberFormatException) {
        println("Error message")
    }

    println("Result:")
    if (param == "lower")
        getLowerFirst(num)
    else
        getHigherFirst(num)
}

fun getLowerFirst(num: Int?) {
    var str = num.toString()
    for (i in str.length - 1 downTo 0) {
        checkPrimeAndPrint(str.substring(i, str.length).reversed())
    }
}

fun getHigherFirst(num: Int?) {
    var str = num.toString()
    for (i in 1..str.length) {
        checkPrimeAndPrint(str.substring(0, i))
    }
}

fun checkPrimeAndPrint(str: String) {
    val num: Int = str.toInt()
    if (isPrime(num))
        println("$num - prime")
    else
        println("$num")
}

fun isPrime(num: Int): Boolean {
    if (num < 2) return false
//    for (m in 2..num - 1)
    for (m in 2..num /2)
        if (num % m == 0) return false
    return true
}

//kotlinc Main.kt -include-runtime -d main.jar
//java -jar main.jar -param lower

//java -jar main.jar -param higher
