package com.example.api2

import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofit_client {

    val apiService: Retrofit_interface
        get() = instance.create(Retrofit_interface::class.java)

    private val instance: Retrofit
            private  get() {
            val gson = GsonBuilder().setLenient().create()

            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
}
