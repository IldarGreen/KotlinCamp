package org.example

fun main() {
    print("The program is running. ")
    var str = ""
    var counter = 4
    while (true) {
        if (++counter == 5) {
            println("Enter a number or type \"exit\" to stop:")
            counter = 0
        } else {
            println("Enter a number:")
        }
        str = readln()
        if (str == "exit") {
            println()
            break
        }
        println()

        var num = inputInt(str)
        if (num != null)
            println(convertNumberToWords(num))

    }
    print("Bye!")
}

fun inputInt(str: String): Int? {
    var x: Int? = null
    try {
        x = str.toInt()
        if (x < -1000000 || x > 1000000) {
            println("The number is out of bounds, try again.")
        }
    } catch (e: NumberFormatException) {
        try {
            str.toDouble()
            println("The number is out of bounds, try again.")
        } catch (e: NumberFormatException) {
            println("Incorrect format, try again.")
        }
    }
    return x
}

fun convertNumberToWords(number: Int): String {
    if (number < 0) {
        return "минус ${convertNumberToWords(-number)}"
    }
    if (number == 0) {
        return "ноль"
    }

    val million = (number) / 1000000
    val thousand = (number - million * 1000000) / 1000
    val toThousand = number % 1000

    var result = ""
    if (million > 0)
        result += numberToWords(million, 1) + ' '
    if (thousand > 0)
        result += numberToWords(thousand, 2) + ' '
    if (toThousand > 0)
        result += numberToWords(toThousand, 3)
    return result
}

fun numberToWords(numeralValue: Int, index: Int): String {
    val hundreds = numeralValue / 100
    val decimal = (numeralValue - hundreds * 100) / 10
    val units = numeralValue % 10

    var numText = if (decimal == 1) {
        "${sampleText[2][hundreds]} ${sample11to19[units]}"
    } else {
        "${sampleText[2][hundreds]} ${sampleText[1][decimal]} ${sampleText[0][units]}"
    }

    if (index == 2) {
        if (units == 1 && decimal != 1) numText += "на "
        else if (units == 2 && decimal != 1) numText += "е "
        else if (units > 1 && decimal != 1) numText += " "
    } else {
        if (units == 1 && decimal != 1) numText += "ин "
        if (units == 2 && decimal != 1) numText += "а "
        else if (units != 0 && decimal != 1) numText += " "
    }

    numText += textMillion[index][index]

    return numText.trim()
}

val sampleText = arrayOf(
    arrayOf("", "од", "дв", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"),
    arrayOf("", "десять", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"),
    arrayOf("", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот")
)

val sample11to19 = arrayOf("", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семьнадцать", "восемьнадцать", "девятнадцать")

val textMillion = arrayOf(
    arrayOf("", "", "", ""),
    arrayOf("", "миллионы", "тысячь", ""),
    arrayOf("", "миллион", "тысяча", ""),
    arrayOf("", "миллиона", "тысячи", "")
)
