package com.AGIndigo8.dailydopamine

import android.widget.EditText


data class ComponentToTask (
    val taskName: EditText,
    val taskDescription: EditText,
    val taskMinimum: EditText
){
    fun buildTask() : Task {
        checkFields()
        val task = Task()
        task.name = taskName.text.toString()
        task.description = taskDescription.text.toString()
        task.minumumRequirement = taskMinimum.text.toString().toString()
        return task
    }

    private fun checkFields(){
        val offendingFields = mutableListOf<EditText>()
        if (taskName.text.isEmpty()) offendingFields.add(taskName)
        if (taskMinimum.text.isEmpty()) offendingFields.add(taskMinimum)
        if (offendingFields.isNotEmpty()) throw NewTaskFieldException(offendingFields)
    }
}

class NewTaskFieldException ( offendingFields: List<EditText>) 
: Exception( "NewTask is missing a required field"){
    val offendingFields = offendingFields
}