package com.app.todominimalistapp

data class Task(
    var name: String? = null,
    var description: String? = null,
    var isChecked: Boolean = false,
    var date: String? = null
)