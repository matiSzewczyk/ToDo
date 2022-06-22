package com.example.todoremake

import android.view.View
import android.widget.TextView

interface CustomClickInterface {
    fun onClickListener(position: Int, view: View)
    fun onCheckedChangeListener(
        adapterPosition: Int,
        checked: Boolean,
        taskName: TextView,
        taskDescription: TextView
    )
}