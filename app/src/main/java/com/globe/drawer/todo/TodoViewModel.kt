package com.globe.todo

import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch

class TodoViewModel(private val db: TodoDb): ViewModel() {

    val todosLiveData: LiveData<List<Todo>> = db.todoDao().getAllFlow().asLiveData()

    fun addTodo(todo: Todo){
        viewModelScope.launch {
            db.todoDao().insert(todo).toInt()
        }
    }
    fun deleteTodo(todo: Todo?) {
        if (todo != null) {
            viewModelScope.launch {
                db.todoDao().delete(todo)
            }
        }
    }

    fun updateTodo(todo: Todo?) {
        if (todo != null) {
            viewModelScope.launch {
                db.todoDao().update(todo)
            }
        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])

                return TodoViewModel(
                    (application as TodoApp).todoDb,
                ) as T
            }
        }
    }
}