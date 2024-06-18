package org.example

fun main() {
    try {
        println("Type a response code:")
        val code = readlnOrNull()?.toInt()

        val response = when (code) {
            200, 201 -> Response.Success(code)
            1000 -> Response.Error.InternalErrors.UserIsNotIdentifiedError()
            1001 -> Response.Error.InternalErrors.SessionIsExpiredError()
            1002 -> Response.Error.InternalErrors.NoConnectionError()
            1003 -> Response.Error.InternalErrors.DeviceHasFailedVerificationError()
            else -> Response.Error.UnknownError(code!!)
        }

        println(response)

    } catch (ex: Exception) {
        println("Program error: " + ex.message)
    }
}