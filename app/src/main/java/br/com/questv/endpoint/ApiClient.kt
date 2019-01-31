package br.com.questv.endpoint

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient private constructor() {
  companion object {

    @JvmStatic private val BASE_URL: String = "http://localhost:5000"

    @JvmStatic private val retrofit: Retrofit = Retrofit
      .Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()

    @JvmStatic val instance: ApiRest = retrofit.create(ApiRest::class.java)
  }


}