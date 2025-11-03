package com.example.todolist.data.local


import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task): Long


    @Update
    suspend fun update(task: Task)


    @Delete
    suspend fun delete(task: Task)


    @Query("SELECT * FROM task ORDER BY id DESC")
    fun getAll(): LiveData<List<Task>>
}
