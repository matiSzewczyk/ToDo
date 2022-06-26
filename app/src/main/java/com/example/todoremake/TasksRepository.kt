package com.example.todoremake

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TasksRepository @Inject constructor(){

    private val tasksBox = ObjectBox.store.boxFor(TaskOB::class.java)

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

    fun removeAll() {
        tasksBox.removeAll()
    }

    fun addToCompleted(task: Task) {
        val query = tasksBox
            .query(TaskOB_.taskName.equal(task.name!!))
            .build()
        val result = query.findFirst()
        result?.isChecked = true

        if (result != null) {
            result.completionDate = Constants().DATE
            println("completed at: ${result.completionDate}")
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
