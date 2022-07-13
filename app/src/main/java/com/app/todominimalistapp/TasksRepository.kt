package com.app.todominimalistapp

import io.objectbox.Box
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TasksRepository @Inject constructor(
    private val tasksBox: Box<TaskOB>
){

    fun saveToObjectBox(task: Task) {
        val taskOB = TaskOB(
            0,
            task.name,
            task.description,
            task.isChecked
        )
        tasksBox.put(taskOB)
    }

    fun getObjectBoxList(): MutableList<TaskOB> {
        return tasksBox.all.toMutableList()
    }

    fun addToCompleted(task: Task) {
        val query = tasksBox
            .query(TaskOB_.taskName.equal(task.name!!))
            .build()
        val result = query.findFirst()

        if (result != null) {
            result.isChecked = true
            result.completionDate = Constants().DATE
            tasksBox.put(result)
        }
        query.close()
    }

    fun removeFromBox(task: Task) {
        val query = tasksBox
            .query(TaskOB_.taskName.equal(task.name!!))
            .build()
        val result = query.findFirst()
        if (result != null) {
            tasksBox.remove(result)
        }
    }
}
