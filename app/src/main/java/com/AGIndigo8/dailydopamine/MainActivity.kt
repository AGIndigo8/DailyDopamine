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

    fun setupAddTaskButton(){
        val addTaskButton: Button = findViewById(R.id.addTaskButton)
        addTaskButton.setOnClickListener {
            numTasks++
            val task = Task()
            task.name = "Task" + numTasks
            task.description = "This is a description"
            supportFragmentManager.commit{
                add(R.id.contentLayout, TaskCardFrag.newInstance(task))
            }
        }
    }
}