package com.AGIndigo8.dailydopamine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class NewTask : AppCompatActivity() {

    private lateinit var taskName : EditText
    private lateinit var taskDescription : EditText
    private lateinit var taskMinimum : EditText

    private lateinit var submitButton : Button
    private lateinit var cancelButton : Button
    
    private lateinit var componentToTask : ComponentToTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)
        getViews()
        registerViewsWithConverter()
        setupCancelButton()
    }

    private fun getViews() {
        taskName = findViewById(R.id.clientNameInput)
        taskDescription = findViewById(R.id.clientDescriptionInput)
        taskMinimum = findViewById(R.id.clientMinInput)
        
        submitButton = findViewById(R.id.finalizeAddTask)
        cancelButton = findViewById(R.id.cancelButton)
    }

    private fun registerViewsWithConverter(){
        componentToTask = ComponentToTask(taskName, taskDescription, taskMinimum)
        submitButton.setOnClickListener {
            try {
                val task = componentToTask.buildTask()
                TaskManager.addTask(task)
                finish()
            } catch (e: NewTaskFieldException) {
                e.offendingFields.forEach { it.error = "This field is required" }
            }
        }
    }

    private fun setupCancelButton() {
        cancelButton.setOnClickListener {
            finish()
        }
    }

    private fun setupAddTaskButton() {
        submitButton.setOnClickListener {
            val task = Task()
            task.name = taskName.text.toString()
            task.description = taskDescription.text.toString()
            task.minumumRequirement = taskMinimum.text.toString().toString()
            TaskManager.addTask(task)
            finish()
        }
    }
}