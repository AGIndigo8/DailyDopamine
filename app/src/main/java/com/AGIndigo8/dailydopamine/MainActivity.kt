package com.AGIndigo8.dailydopamine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.fragment.app.commit
import androidx.compose.runtime.Composable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val taskLayout = findViewById<LinearLayout>(R.id.taskLayout)
        val task = Task()
        task.name = "Task 1"
        task.description = "This is a description"
        supportFragmentManager.commit{
            for (i in 0..3) {
                add(R.id.taskLayout, TaskCardFrag.newInstance(task))
            }
        }
    }
}