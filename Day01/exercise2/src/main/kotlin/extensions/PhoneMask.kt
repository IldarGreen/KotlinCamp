package org.example.extensions

fun String.phoneMask(): String {
    val str : String = if (this.length == 11 && (this.startsWith("7") or this.startsWith("8"))) {
        this.substring(1)
    } else if (this.length == 12 && this.startsWith("+7")) {
        this.substring(2)
    } else {
        return this
    }

    var firstNum = "+7"
    var spaceOrDash = "-"

    if (str.startsWith("800")) {
        firstNum = "8"
        spaceOrDash = ""
    }

    return "$firstNum (${str.substring(0, 3)}) " +
            "${str.substring(3, 6)}$spaceOrDash${str.substring(6, 8)}$spaceOrDash${str.substring(8)}"
}