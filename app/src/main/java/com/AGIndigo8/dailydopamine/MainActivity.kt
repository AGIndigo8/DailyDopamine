package com.AGIndigo8.dailydopamine

import android.content.Intent
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
            val intent = Intent(this, NewTask::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val contentLayout: LinearLayout = findViewById(R.id.contentLayout)
        contentLayout.removeAllViews()
        TaskManager.getUserTasks().forEach {
            val taskCardFrag = TaskCardFrag.newInstance(it)
            supportFragmentManager.commit {
                add(R.id.contentLayout, taskCardFrag)
            }
        }
    }

}