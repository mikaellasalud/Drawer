package com.globe.todo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    var onItemDeleted: ((position:Int)->Unit)? = null
    var onItemUpdated: ((position:Int)->Unit)? = null

    var items: ArrayList<Todo>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun addTodo(todo: Todo) {
        items?.add(todo)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = items!!.get(position)
        holder.todo.text = todo.task
        holder.todo.isChecked = todo.done

        if (todo.done) {
            holder.todo.alpha = 0.7f
        } else {
            holder.todo.alpha = 1f
        }

    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    inner class TodoViewHolder(itemView: View) : ViewHolder(itemView) {
        lateinit var todo: CheckBox
        lateinit var ibRemove: ImageButton

        init {
            todo = itemView.findViewById(R.id.cbTask)
            ibRemove = itemView.findViewById(R.id.ibRemove)

            todo.setOnCheckedChangeListener { buttonView, isChecked ->
                val todo = items?.get(adapterPosition)
                todo?.done = isChecked

                if (todo != null) {
                    runCatching {
                        items?.set(adapterPosition, todo)
                        onItemUpdated?.invoke(adapterPosition)
                        notifyItemChanged(adapterPosition)
                    }
                }

                ibRemove.setOnClickListener {
                    runCatching {
                        onItemDeleted?.invoke(adapterPosition)
                        items?.removeAt(adapterPosition)
                        notifyItemRemoved(adapterPosition)
                    }.onFailure {
                        it.printStackTrace()
                    }

                }
            }


        }
    }
}