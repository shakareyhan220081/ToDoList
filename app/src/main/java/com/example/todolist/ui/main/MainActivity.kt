package com.example.todolist.ui.main


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    private val vm: TaskViewModel by viewModels()
    private lateinit var adapter: TaskAdapter


    private val leakObserver = Observer<List<com.example.todolist.data.local.Task>> {
        adapter.submitList(it)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)


        adapter = TaskAdapter(
            onToggle = { vm.toggle(it) },    // BUG: bisa berat di main thread
            onDelete = { vm.delete(it) }     // BUG: bisa berat di main thread
        )


        b.rvTasks.layoutManager = LinearLayoutManager(this)
        b.rvTasks.adapter = adapter


        b.btnAdd.setOnClickListener {
            val text = b.etTask.text.toString()
            vm.add(text)
        }


        vm.tasks.observeForever(leakObserver)
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}
