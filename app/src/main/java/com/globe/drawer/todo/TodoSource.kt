package com.globe.todo

class TodoSource {

    fun getItems(): ArrayList<Todo> {
        return todos
    }

    fun addTodo(newTodo: String) {
        todos.add(Todo(0,newTodo))
    }

    private val todos = ArrayList<Todo>()

    init {
        /*todos.add(Todo("Clean the house",false))
        todos.add(Todo("Walk the dog",true))
        todos.add(Todo("Rewrite Notes",false))
        todos.add(Todo("Study",false))
        todos.add(Todo("Check Email",true))
        todos.add(Todo("Pay the bills",false))
        todos.add(Todo("Prepare Breakfast",true))
        todos.add(Todo("Wash the dishes",true))
        todos.add(Todo("Meet up with friends for Dinner",false))
        todos.add(Todo("Attend the online meeting",true))*/
    }
}