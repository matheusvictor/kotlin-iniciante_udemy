package com.example.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tasks.service.constants.TaskConstants
import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.listener.ValidationListener
import com.example.tasks.service.model.HeaderModel
import com.example.tasks.service.repository.PersonRepository
import com.example.tasks.service.repository.PriorityRepository
import com.example.tasks.service.repository.local.SecurityPreferences
import com.example.tasks.service.repository.remote.RetrofitClient

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val mPersonRepository = PersonRepository(application)
    private val mPriorityRepository = PriorityRepository(application)

    private val mSharedPreferences = SecurityPreferences(application)

    private val mLoginSuccessed = MutableLiveData<ValidationListener>()
    var loginSuccessed: LiveData<ValidationListener> = mLoginSuccessed

    private val mLoggedUser = MutableLiveData<Boolean>()
    var loggedUser: LiveData<Boolean> = mLoggedUser

    /**
     * Faz login usando API
     */
    fun doLogin(email: String, password: String) {
        mPersonRepository.login(email, password, object : APIListener {

            override fun onSuccess(model: HeaderModel) {

                mSharedPreferences.store(TaskConstants.SHARED.TOKEN_KEY, model.token)
                mSharedPreferences.store(TaskConstants.SHARED.PERSON_NAME, model.name)
                mSharedPreferences.store(TaskConstants.SHARED.PERSON_KEY, model.personKey)

                RetrofitClient.addHeaders(model.token, model.personKey)

                // Como não foi passado nenhuma mensagem no parâmetro, o retorno é true
                mLoginSuccessed.value = ValidationListener()
            }

            override fun onFailure(message: String) {
                mLoginSuccessed.value = ValidationListener(message)
            }
        })
    }

    /**
     * Verifica se usuário está logado
     */
    fun verifyLoggedUser() {

        val token = mSharedPreferences.get(TaskConstants.SHARED.TOKEN_KEY)
        val personKey = mSharedPreferences.get(TaskConstants.SHARED.PERSON_KEY)

        RetrofitClient.addHeaders(token, personKey)

        val isLogged = (token != "" && personKey != "")

        if (!isLogged) {
            mPriorityRepository.allPriorities()
        }

        mLoggedUser.value = isLogged

    }

}
