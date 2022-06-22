package com.example.todoremake

import android.widget.LinearLayout
import android.widget.TextView

interface CustomClickInterface {
    fun onClickListener(position: Int, hiddenLayout: LinearLayout)
    fun onCheckedChangeListener(
        adapterPosition: Int,
        checked: Boolean,
        taskName: TextView,
        taskDescription: TextView
    )
}