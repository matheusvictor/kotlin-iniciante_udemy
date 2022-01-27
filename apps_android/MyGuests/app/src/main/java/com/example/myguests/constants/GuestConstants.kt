package com.example.myguests.constants

class GuestConstants private constructor() {

    companion object {
        const val GUESTID = "guestID"
    }

    object FILTER {
        const val EMPTY = 0
        const val CONFIRMED = 1
        const val DENIED = 2
    }
}
