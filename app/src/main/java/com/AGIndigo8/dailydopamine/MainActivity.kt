package com.AGIndigo8.dailydopamine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.commit
import androidx.compose.runtime.Composable

class MainActivity : AppCompatActivity() {
    var numTasks = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupAddTaskButton()
    }

    fun setupAddTaskButton() {
        val addTaskButton: Button = findViewById(R.id.addTaskButton)

        addTaskButton.setOnClickListener {
            val task = Task()
            task.name = "Task" + TaskManager.getTaskCount()
            task.description = "This is a description"
            TaskManager.addTask(task)
            val taskCardFrag = TaskCardFrag.newInstance(task)
            supportFragmentManager.commit {
                add(R.id.contentLayout, taskCardFrag)
            }
        }
    }

}