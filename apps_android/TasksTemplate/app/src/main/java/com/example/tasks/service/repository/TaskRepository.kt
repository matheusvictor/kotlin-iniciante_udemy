package com.example.tasks.service.repository

import android.content.Context
import com.example.tasks.R
import com.example.tasks.service.constants.TaskConstants
import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.model.TaskModel
import com.example.tasks.service.repository.remote.RetrofitClient
import com.example.tasks.service.repository.remote.TaskService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskRepository(val context: Context) {

    private val mRemote = RetrofitClient.createService(TaskService::class.java)

    fun allTasks(listener: APIListener<List<TaskModel>>) {
        val call: Call<List<TaskModel>> = mRemote.listAllTasks()
        list(call, listener)
    }

    fun nextWeek(listener: APIListener<List<TaskModel>>) {
        val call: Call<List<TaskModel>> = mRemote.nextWeek()
        list(call, listener)
    }

    fun overDue(listener: APIListener<List<TaskModel>>) {
        val call: Call<List<TaskModel>> = mRemote.overdue()
        list(call, listener)
    }

    private fun list(call: Call<List<TaskModel>>, listener: APIListener<List<TaskModel>>) {
        call.enqueue(object : Callback<List<TaskModel>> {
            override fun onResponse(
                call: Call<List<TaskModel>>,
                response: Response<List<TaskModel>>
            ) {
                if (response.code() != TaskConstants.HTTP.SUCCESS) {
                    val apiMessageValidation =
                        Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(apiMessageValidation)
                } else {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<List<TaskModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

        })
    }

    fun createTask(task: TaskModel, listener: APIListener<Boolean>) {
        val call: Call<Boolean> =
            mRemote.createNewTask(task.priorityId, task.description, task.dueDate, task.complete)

        call.enqueue(object : Callback<Boolean> {

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {

                if (response.code() != TaskConstants.HTTP.SUCCESS) {
                    val apiMessageValidation =
                        Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(apiMessageValidation)
                } else {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

        })
    }

    fun updateTask(task: TaskModel, listener: APIListener<Boolean>) {
        val call: Call<Boolean> =
            mRemote.updateTask(
                task.id,
                task.priorityId,
                task.description,
                task.dueDate,
                task.complete
            )

        call.enqueue(object : Callback<Boolean> {

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {

                if (response.code() != TaskConstants.HTTP.SUCCESS) {
                    val apiMessageValidation =
                        Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(apiMessageValidation)
                } else {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

        })
    }

    fun loadTask(id: Int, listener: APIListener<TaskModel>) {
        val call: Call<TaskModel> = mRemote.loadTaskById(id)

        call.enqueue(object : Callback<TaskModel> {

            override fun onResponse(call: Call<TaskModel>, response: Response<TaskModel>) {

                if (response.code() != TaskConstants.HTTP.SUCCESS) {
                    val apiMessageValidation =
                        Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(apiMessageValidation)
                } else {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<TaskModel>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

        })
    }

    fun deleteTask(id: Int, listener: APIListener<Boolean>) {
        val call: Call<Boolean> = mRemote.deleteTask(id)

        call.enqueue(object : Callback<Boolean> {

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.code() != TaskConstants.HTTP.SUCCESS) {
                    val apiMessageValidation =
                        Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(apiMessageValidation)
                } else {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

        })
    }

    fun updateStatus(id: Int, isComplete: Boolean, listener: APIListener<Boolean>) {

        val call: Call<Boolean> = if (isComplete) {
            mRemote.completeTask(id)
        } else {
            mRemote.undoTask(id)
        }

        call.enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.code() != TaskConstants.HTTP.SUCCESS) {
                    val apiMessageValidation =
                        Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(apiMessageValidation)
                } else {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }

}
