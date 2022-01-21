package com.example.tasks.service.repository.remote

import com.example.tasks.service.constants.TaskConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    /*
    Singleton
     */
    companion object {

        private lateinit var retrofit: Retrofit

        private var personKey: String = ""
        private var tokenKey: String = ""

        private fun getRetrofitInstance(): Retrofit {

            val httpClient = OkHttpClient.Builder()

            httpClient.addInterceptor(object : Interceptor {

                //chain --> requisição que está ocorrendo
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request()
                        .newBuilder()
                        .addHeader(TaskConstants.HEADER.TOKEN_KEY, tokenKey)
                        .addHeader(TaskConstants.HEADER.PERSON_KEY, personKey)
                        .build()
                    return chain.proceed(request)
                }

            })

            if (!Companion::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(TaskConstants.API_URL.BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

        fun addHeaders(token: String, personKey: String) {
            this.tokenKey = token
            this.personKey = personKey
        }

        fun <S> createService(serviceClass: Class<S>): S {
            return getRetrofitInstance().create(serviceClass)
        }
    }

}
