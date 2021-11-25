package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddTaskActivity : AppCompatActivity() {
    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val actionBar = supportActionBar
        actionBar!!.title = "Add Task"
        actionBar.setDisplayHomeAsUpEnabled(true)

        val addButton = findViewById<Button>(R.id.add_button) as MaterialButton
        val editText = findViewById<EditText>(R.id.newTaskInput) as TextInputEditText

        database = FirebaseDatabase.getInstance().reference

        addButton.setOnClickListener{
            val taskText = editText.text!!
            val todoItemData = ToDoModel.createList()
            todoItemData.itemDataText = taskText.toString()
            todoItemData.done = false

            val newItemData = database.child("todo").push()
            todoItemData.UID = newItemData.key

            newItemData.setValue(todoItemData)

            onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}