package com.app.todominimalistapp

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Constants {
     private val current = LocalDateTime.now()

    private val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val DATE = current.format(formatter)
}