package org.example

sealed class Response {
    abstract val code: Int

    class Success(override val code: Int) : Response() {
        val message = "The request processed successfully"
    }

    sealed class Error : Response() {
        abstract val title: String
        abstract val description: String

        sealed class InternalErrors : Error() {
            class UserIsNotIdentifiedError() : InternalErrors() {
                override val code = 1000
                override val title = "The user is not identified"
                override val description = "The user has not been identified. Please re-enter your login and password."
            }

            class SessionIsExpiredError() : InternalErrors() {
                override val code = 1001
                override val title = "The session is expired"
                override val description = "This connection has expired. Please reconnect."
            }

            class NoConnectionError() : InternalErrors() {
                override val code = 1002
                override val title = "No connection"
                override val description = "There is no internet connection. Try later."
            }

            class DeviceHasFailedVerificationError() : InternalErrors() {
                override val code = 1003
                override val title = "The device has failed the verification"
                override val description = "There is a device which fails verification. Try again."
            }

        }

        class UnknownError(override val code: Int) : Error() {
            override val title = "Error Code: $code"
            override val description = "Unknown error. Look on StackOverflow"
        }

    }

    override fun toString(): String {
        return when (this) {
            is Success -> "\n${this::class.simpleName}:\n" +
                    "\tCode: $code\n" +
                    "\tMessage: $message"

            is Error -> "\n${this::class.simpleName}:\n" +
                    "\tCode: $code\n" +
                    "\tTitle: $title\n" +
                    "\tDescription: $description"
        }
    }

}
