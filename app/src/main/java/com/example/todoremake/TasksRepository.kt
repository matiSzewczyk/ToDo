package com.example.todoremake

import javax.inject.Inject

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
        if (result != null)
            tasksBox.put(result)
        query.close()
    }
}
