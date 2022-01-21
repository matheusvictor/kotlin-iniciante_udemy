package com.example.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tasks.service.constants.TaskConstants
import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.listener.ValidationListener
import com.example.tasks.service.model.TaskModel
import com.example.tasks.service.repository.TaskRepository

class AllTasksViewModel(application: Application) : AndroidViewModel(application) {

    private val mTaskRepository = TaskRepository(application)
    private var mTaskFilter = 0

    private val mValidation = MutableLiveData<ValidationListener>()
    var validation: LiveData<ValidationListener> = mValidation

    private val mTaskList = MutableLiveData<List<TaskModel>>()
    var tasks: LiveData<List<TaskModel>> = mTaskList

    fun listAllTasks(taskFilter: Int) {
        mTaskFilter = taskFilter

        val listener = object : APIListener<List<TaskModel>> {
            override fun onSuccess(model: List<TaskModel>) {
                mTaskList.value = model
            }

            override fun onFailure(message: String) {
                mTaskList.value = arrayListOf()
                mValidation.value = ValidationListener(message)
            }
        }

        if (mTaskFilter == TaskConstants.FILTER.ALL) {
            mTaskRepository.allTasks(listener)
        } else if (mTaskFilter == TaskConstants.FILTER.NEXT) {
            mTaskRepository.nextWeek(listener)
        } else {
            mTaskRepository.overDue(listener)
        }
    }

    fun delete(id: Int) {
        mTaskRepository.deleteTask(id, object : APIListener<Boolean> {
            override fun onSuccess(model: Boolean) {
                listAllTasks(mTaskFilter)
                mValidation.value = ValidationListener()
            }

            override fun onFailure(message: String) {
                mValidation.value = ValidationListener(message)
            }
        })
    }

    fun complete(id: Int) {
        updateStatus(id, true)
    }

    fun undo(id: Int) {
        updateStatus(id, false)
    }

    private fun updateStatus(id: Int, complete: Boolean) {
        mTaskRepository.updateStatus(id, complete, object : APIListener<Boolean> {
            override fun onSuccess(model: Boolean) {
                listAllTasks(mTaskFilter)
            }

            override fun onFailure(message: String) {
            }
        })
    }

}
