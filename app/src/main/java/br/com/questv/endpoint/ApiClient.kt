package br.com.questv.endpoint

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient private constructor() {
  companion object {

    @JvmStatic val instance = ApiClient()

    @JvmStatic private val BASE_URL: String = ""

    @JvmStatic private val retrofit: Retrofit = Retrofit
      .Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()

    @JvmStatic private val apiRest: ApiRest = retrofit.create(ApiRest::class.java)
  }


}