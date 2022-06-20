package com.example.todoremake

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class TaskOB(
    @Id
    var taskId: Long? = 0,
    var taskName: String? = null,
    var taskDescription: String? = null,
    var isChecked: Boolean = false
)
