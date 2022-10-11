package com.globe.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView

class TodoMainActivity : AppCompatActivity() {

    private lateinit var todoRv: RecyclerView
    private lateinit var etInput: EditText
    private lateinit var ibAdd: ImageButton

    private lateinit var adapter: TodoAdapter
    private val viewModel: TodoViewModel by viewModels { TodoViewModel.Factory }

    private fun setupView() {
        todoRv = findViewById(R.id.rvTodo)
        etInput = findViewById(R.id.etInput)
        ibAdd = findViewById(R.id.ibAdd)
    }

    private fun setupAdapter() {
        adapter = TodoAdapter()
        todoRv.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todomain)
        setupView()
        setupAdapter()
        addEventListener()

        viewModel.todosLiveData.observe(this) { todoList ->
            adapter.items = ArrayList(todoList)
        }
    }

    private fun addEventListener() {
        adapter.onItemDeleted = { position ->
            viewModel.deleteTodo(adapter.items?.get(position))
        }

        adapter.onItemUpdated = { position ->
            viewModel.updateTodo(adapter.items?.get(position))
        }

        ibAdd.setOnClickListener {
            val task = etInput.text.toString().trim()
            if(task.isNotEmpty()){
                etInput.setText("")
                val newTodo = Todo(0, task)
                viewModel.addTodo(newTodo)
            }
        }
    }
}