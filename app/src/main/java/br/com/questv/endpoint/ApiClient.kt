package br.com.questv.endpoint

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.InetAddress

class ApiClient private constructor() {
  companion object {

//        @JvmStatic private val BASE_URL: String = "http://10.0.2.2:5000"
//    @JvmStatic private val BASE_URL: String = "http://192.168.25.44:5000"
//    @JvmStatic private val BASE_URL: String = "http://192.168.0.121:5000"
//    @JvmStatic private val BASE_URL: String = "http://10.0.3.170:5000" //Local
//    @JvmStatic private val BASE_URL: String = "http://192.168.25.21:5000" //Local
    @JvmStatic private val BASE_URL: String = "https://questv-api.herokuapp.com" //Heroku
//    @JvmStatic private val BASE_URL: String = "http://192.168.0.100:5000"

    @JvmStatic private val retrofit: Retrofit = Retrofit
      .Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()

    @JvmStatic val instance: ApiRest = retrofit.create(ApiRest::class.java)
  }


}