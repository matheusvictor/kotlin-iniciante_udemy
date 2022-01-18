package com.example.tasks.service.repository

import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.model.HeaderModel
import com.example.tasks.service.repository.remote.PersonService
import com.example.tasks.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonRepository {

    private val mRetrofitInstance = RetrofitClient.createService(PersonService::class.java)

    fun login(email: String, password: String, listener: APIListener) {
        val call: Call<HeaderModel> = mRetrofitInstance.login(email, password)
        // Realiza chamada assíncrona:
        call.enqueue(
            object : Callback<HeaderModel> {

                /*
                A falha só ocorre se a requisição para a API não obteve sucesso.
                O método onFailure será será chamado se a requisição não for feita com sucesso, do
                contrário, procederá normalmente para onResponse - podendo variar apenas o código
                de retorno da requisição.
                 */
                override fun onFailure(call: Call<HeaderModel>, t: Throwable) {
                    listener.onFailure(t.message.toString())
                }

                override fun onResponse(call: Call<HeaderModel>, response: Response<HeaderModel>) {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }
        )
    }

}
