package com.example.todoremake

data class Task(
    var name: String? = null,
    var description: String? = null,
    var isChecked: Boolean? = false
)